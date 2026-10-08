Enterprise Coding Standards for Playwright + TypeScript
Tests, Page Objects, Fixtures, Hooks & Execution Standards
This is a practical, opinionated coding standards document you can drop into any enterprise Playwright project. Every rule includes a rationale and a good/bad code example. Follow this and your framework will scale from 100 to 10,000+ tests without becoming unmaintainable.

SECTION 1 — CODING STANDARDS PHILOSOPHY
Core Principles
Principle	Meaning	Why It Matters
Readability > Cleverness	Tests should read like business requirements	Non-technical stakeholders can review them
Isolation > Reuse	Each test is independent	Enables parallelism, prevents cascade failures
Explicit > Implicit	Assertions show expected behavior	Failures are self-explanatory
Stable > Fast	Prefer reliable locators over fast selectors	Reduces flakiness, maintenance cost
Semantic > Structural	Locate by role/label, not CSS	Resilient to DOM refactors
Domain > Technical	Method names use business language	transferFunds() not clickButton()
The Golden Rules
No assertions in Page Objects — except self-validating expect*() methods.

No locators in test files — all locators live in Page Objects or Components.

No page.waitForTimeout() — ever, unless documented with a strong reason.

No hardcoded test data — use factories, fixtures, or environment variables.

No shared mutable state between tests — each test creates its own data.

No conditional logic in tests — if if/else appears, split into separate tests.

No business logic in test files — move to helpers or Page Objects.

SECTION 2 — FILE NAMING & DIRECTORY STANDARDS
File Naming Conventions
Type	Convention	Example
Page Object (UI)	kebab-case.page.ts	login.page.ts, fund-transfer.page.ts
Page Object (API)	kebab-case.api.ts	accounts.api.ts, auth.api.ts
Component	kebab-case.component.ts	header.component.ts
Fixture	kebab-case.fixtures.ts	app.fixtures.ts
Utility	kebab-case.utils.ts or kebab-case.ts	date-utils.ts, logger.ts
Helper	kebab-case.helper.ts	banking.helper.ts
Test (Smoke)	feature.smoke.spec.ts	login.smoke.spec.ts
Test (Regression)	feature.regression.spec.ts	transfer.regression.spec.ts
Test (API)	feature.api.spec.ts	accounts.api.spec.ts
Test (Negative)	feature.negative.spec.ts	login.negative.spec.ts
Schema	entity.schema.ts	account.schema.ts
Setup	name.setup.ts	auth.setup.ts
Types	entity.types.ts	user.types.ts
Rationale: Predictable naming means any team member can locate a file in seconds. Test type suffixes allow CI filtering via glob patterns (**/*.smoke.spec.ts).

SECTION 3 — PAGE OBJECT CLASS STANDARDS
3.1 Structure of a Page Object
Every Page Object MUST follow this structure:

typescript
// 1. Imports
import { Page, Locator, expect } from '@playwright/test';
import { BasePage } from '@pages/base.page';

// 2. Class with clear responsibility
export class FundTransferPage extends BasePage {
// 3. URL — if the page has a stable route
readonly url = '/transfers/new';

// 4. Locators — readonly, initialized in constructor
readonly fromAccountDropdown: Locator;
readonly toAccountDropdown: Locator;
readonly amountInput: Locator;
readonly submitButton: Locator;
readonly successBanner: Locator;
readonly errorBanner: Locator;

// 5. Constructor — assign locators only, no async work
constructor(page: Page) {
super(page);
this.fromAccountDropdown = page.getByLabel('From Account');
this.toAccountDropdown = page.getByLabel('To Account');
this.amountInput = page.getByLabel('Amount');
this.submitButton = page.getByRole('button', { name: 'Transfer' });
this.successBanner = page.getByTestId('transfer-success');
this.errorBanner = page.getByTestId('transfer-error');
}

// 6. Navigation methods
async navigate() {
await this.page.goto(this.url);
await expect(this.submitButton).toBeVisible();
}

// 7. Atomic action methods — one interaction each
async selectFromAccount(accountId: string) {
await this.fromAccountDropdown.selectOption(accountId);
}

async selectToAccount(accountId: string) {
await this.toAccountDropdown.selectOption(accountId);
}

async enterAmount(amount: string) {
await this.amountInput.fill(amount);
}

async clickSubmit() {
await this.submitButton.click();
}

// 8. Business methods — compose atomic actions
async transferFunds(from: string, to: string, amount: string) {
await this.selectFromAccount(from);
await this.selectToAccount(to);
await this.enterAmount(amount);
await this.clickSubmit();
}

// 9. Self-validating assertion methods
async expectTransferSuccess() {
await expect(this.successBanner).toBeVisible();
await expect(this.successBanner).toContainText('Transfer Complete');
}

async expectTransferError(message: string) {
await expect(this.errorBanner).toBeVisible();
await expect(this.errorBanner).toContainText(message);
}

// 10. State readers — return values, no assertions
async getSuccessMessage(): Promise<string> {
await expect(this.successBanner).toBeVisible();
return (await this.successBanner.textContent()) ?? '';
}
}
3.2 Page Object Do's and Don'ts
✅ DO	❌ DON'T
One Page Object per page/route	One class with methods for multiple pages
Locators as readonly properties	Locators created inside methods repeatedly
getByRole, getByLabel, getByTestId	CSS, XPath, nth-child()
Business-named methods (transferFunds)	Technical-named methods (clickButton2)
Return values or self-validating assertions	Assertions on every internal step
Extend a shared BasePage	Duplicate waitForLoadState, toast helpers
Constructor only assigns locators	Constructor does async work or navigates
Small, focused methods	100-line methods doing everything
3.3 BasePage Standard
typescript
// pages/base.page.ts
import { Page, Locator, expect } from '@playwright/test';

export abstract class BasePage {
constructor(readonly page: Page) {}

// Optional: URL for pages with stable routes
abstract readonly url?: string;

async navigate() {
if (!this.url) throw new Error(`${this.constructor.name} has no url`);
await this.page.goto(this.url);
await this.waitForLoad();
}

async waitForLoad() {
await this.page.waitForLoadState('domcontentloaded');
}

async getToastMessage(): Promise<string> {
const toast = this.page.getByRole('alert');
await expect(toast).toBeVisible();
return (await toast.textContent()) ?? '';
}

async expectUrl(pattern: string | RegExp) {
await expect(this.page).toHaveURL(pattern);
}

async expectTitle(title: string | RegExp) {
await expect(this.page).toHaveTitle(title);
}
}
3.4 Component Object Standard
Use Component Objects when the same UI element (header, modal, table) appears across multiple pages.

typescript
// components/data-table.component.ts
import { Locator, expect } from '@playwright/test';

export class DataTableComponent {
readonly root: Locator;
readonly rows: Locator;

constructor(root: Locator) {
this.root = root;
this.rows = root.getByRole('row');
}

async rowCount(): Promise<number> {
return this.rows.count();
}

rowByText(text: string): Locator {
return this.rows.filter({ hasText: text });
}

async clickActionInRow(rowText: string, actionName: string) {
await this.rowByText(rowText)
.getByRole('button', { name: actionName })
.click();
}

async expectRowExists(text: string) {
await expect(this.rowByText(text)).toBeVisible();
}
}
Usage in a Page Object:

typescript
// pages/accounts.page.ts
import { DataTableComponent } from '@components/data-table.component';

export class AccountsPage extends BasePage {
readonly url = '/accounts';
readonly accountsTable: DataTableComponent;

constructor(page: Page) {
super(page);
this.accountsTable = new DataTableComponent(page.getByTestId('accounts-table'));
}
}
SECTION 4 — TEST FILE STANDARDS
4.1 Test File Structure
Every test file MUST follow this order:

typescript
// 1. Imports
import { test, expect } from '@fixtures/app.fixtures';
import { createUser, createBankAccount } from '@utils/data-factory';
import { logger } from '@utils/logger';

// 2. Suite description with domain tags
test.describe('Fund Transfer @banking @transfer', () => {

// 3. Suite-level hooks (only if truly needed)
test.beforeEach(async ({ loginPage }) => {
await loginPage.navigate();
await loginPage.login(process.env.USER_EMAIL!, process.env.USER_PASSWORD!);
});

// 4. Positive scenarios
test('user transfers funds between own accounts @smoke @critical', async ({
transferPage,
}) => {
await transferPage.navigate();
await transferPage.transferFunds('12345', '67890', '100.00');
await transferPage.expectTransferSuccess();
});

// 5. Negative scenarios (grouped)
test.describe('Negative scenarios @negative', () => {
test('rejects transfer with insufficient funds', async ({ transferPage }) => {
await transferPage.navigate();
await transferPage.transferFunds('12345', '67890', '9999999');
await transferPage.expectTransferError('Insufficient funds');
});

    test('rejects transfer above daily limit', async ({ transferPage }) => {
      await transferPage.navigate();
      await transferPage.transferFunds('12345', '67890', '100000');
      await transferPage.expectTransferError('Daily limit exceeded');
    });

});

// 6. Data-driven scenarios
const invalidAmounts = ['0', '-100', 'abc', ''];
for (const amount of invalidAmounts) {
test(`rejects invalid amount: "${amount}"`, async ({ transferPage }) => {
await transferPage.navigate();
await transferPage.transferFunds('12345', '67890', amount);
await transferPage.expectTransferError('Invalid amount');
});
}
});
4.2 Test Naming Standards
Format: <actor> <action> <outcome> [@tag]

✅ Good	❌ Bad
user transfers funds between own accounts	test1
admin can deactivate user account	verify admin
rejects login with expired password	login fails
downloads PDF statement for selected period	click download
Rationale: The test name becomes the failure report headline. It should tell a product owner what broke.

4.3 Test Body Standards (AAA Pattern)
typescript
test('user transfers funds between own accounts @smoke', async ({ loginPage, transferPage }) => {
// ARRANGE — set up preconditions (usually in beforeEach)
// Already handled: login via beforeEach

// ACT — perform the action under test
await transferPage.navigate();
await transferPage.transferFunds('12345', '67890', '100.00');

// ASSERT — verify the outcome
await transferPage.expectTransferSuccess();
await expect(transferPage.page.getByTestId('balance-updated')).toBeVisible();
});
4.4 Test Do's and Don'ts
✅ DO	❌ DON'T
Use test.describe to group related scenarios	One giant test.describe with 200 tests
Tag tests: @smoke, @regression, @critical	Untagged tests
Use fixtures for Page Objects	new LoginPage(page) inside every test
Use data factories for unique data	Hardcoded email/order IDs
Assertions in the test or POM expect* methods	Assertions in beforeEach that hide failures
One logical assertion group per test	One test asserting 20 different things
Nested test.describe for negative scenarios	Mixing positive and negative in same block
test.step() for readable multi-step flows	Long uncommented sequences
4.5 Using test.step for Complex Flows
typescript
test('user transfers funds and verifies balance @smoke', async ({ loginPage, transferPage, dashboardPage }) => {
await test.step('Login as premium user', async () => {
await loginPage.navigate();
await loginPage.login(process.env.USER_EMAIL!, process.env.USER_PASSWORD!);
await loginPage.expectLoginSuccess();
});

await test.step('Perform fund transfer', async () => {
await transferPage.navigate();
await transferPage.transferFunds('12345', '67890', '100.00');
await transferPage.expectTransferSuccess();
});

await test.step('Verify balance updated on dashboard', async () => {
await dashboardPage.navigate();
await dashboardPage.expectBalance('900.00');
});
});
Rationale: Steps appear in Allure/Playwright HTML reports, making failures pinpointable without reading logs.

4.6 Test Data Standards
typescript
// ✅ DO — unique data per test
test('user registers with new email', async ({ registerPage }) => {
const user = createUser(); // Faker generates unique email
await registerPage.register(user);
await registerPage.expectRegistrationSuccess();
});

// ❌ DON'T — shared, hardcoded data
test('user registers with new email', async ({ registerPage }) => {
await registerPage.register({
email: 'test@example.com', // Fails on second parallel run
password: 'Test123!',
});
});
4.7 Assertions Standards
typescript
// ✅ DO — business outcome assertions
await expect(page.getByRole('heading', { name: 'Welcome, John' })).toBeVisible();
await expect(page.getByTestId('account-balance')).toHaveText('$1,000.00');

// ✅ DO — use specific matchers
await expect(row).toHaveCount(3);
await expect(button).toBeEnabled();
await expect(input).toHaveValue('100.00');

// ❌ DON'T — implementation-detail assertions
await expect(page.locator('div:nth-child(2) > span')).toHaveText('...');
await expect(page.locator('#user-12345')).toBeVisible();

// ❌ DON'T — soft/weak assertions
expect(await page.getByText('Success').isVisible()).toBeTruthy();

// ✅ DO — auto-waiting assertion
await expect(page.getByText('Success')).toBeVisible();
SECTION 5 — FIXTURES STANDARDS
5.1 When to Use Fixtures vs. beforeEach
Scenario	Use Fixture	Use beforeEach
Injecting Page Objects	✅	❌
API client injection	✅	❌
Authentication state (per role)	✅	❌
Custom test data creation	✅	⚠️
Cleanup after every test	✅ (via fixture teardown)	✅
Login flow (UI)	⚠️ Prefer storageState	✅
Resetting application state	❌	✅
Rule of thumb: Fixtures for dependencies, beforeEach for actions.

5.2 Standard Fixture File
typescript
// fixtures/app.fixtures.ts
import { test as base, expect } from '@playwright/test';
import { LoginPage } from '@pages/login.page';
import { DashboardPage } from '@pages/dashboard.page';
import { FundTransferPage } from '@pages/fund-transfer.page';
import { BeneficiaryPage } from '@pages/beneficiary.page';
import { ApiClient } from '@api/api-client';
import { AuthApi } from '@api/auth.api';

// Type definition for all custom fixtures
export type AppFixtures = {
// UI Page Objects
loginPage: LoginPage;
dashboardPage: DashboardPage;
transferPage: FundTransferPage;
beneficiaryPage: BeneficiaryPage;

// API clients
apiClient: ApiClient;
authApi: AuthApi;
};

export const test = base.extend<AppFixtures>({
// Page Objects — one instance per test
loginPage: async ({ page }, use) => {
await use(new LoginPage(page));
},
dashboardPage: async ({ page }, use) => {
await use(new DashboardPage(page));
},
transferPage: async ({ page }, use) => {
await use(new FundTransferPage(page));
},
beneficiaryPage: async ({ page }, use) => {
await use(new BeneficiaryPage(page));
},

// API clients
apiClient: async ({ request }, use) => {
const client = new ApiClient(request);
await use(client);
},
authApi: async ({ request }, use) => {
await use(new AuthApi(request));
},
});

export { expect };
5.3 Worker-Scoped Fixtures (Advanced)
Use when a resource is expensive to create (e.g., API token fetched once per worker).

typescript
// fixtures/app.fixtures.ts (extended)
type WorkerFixtures = {
workerToken: string;
};

export const test = base.extend<AppFixtures, WorkerFixtures>({
// ...page fixtures above...

workerToken: [
async ({ request }, use) => {
const response = await request.post('/auth/token', {
data: {
username: process.env.API_USER,
password: process.env.API_PASSWORD,
},
});
const { token } = await response.json();
await use(token); // Created once per worker, reused across tests
},
{ scope: 'worker' },
],
});
5.4 Fixtures with Teardown (Cleanup)
typescript
// fixtures/data.fixtures.ts
import { test as base } from '@playwright/test';
import { createBankAccount } from '@utils/data-factory';

type DataFixtures = {
testAccount: { id: string; accountNumber: string };
};

export const test = base.extend<DataFixtures>({
testAccount: async ({ request }, use) => {
// SETUP — create a test account via API
const response = await request.post('/test-api/accounts', {
data: createBankAccount(),
});
const account = await response.json();

    // YIELD to the test
    await use(account);

    // TEARDOWN — clean up even if test fails
    await request.delete(`/test-api/accounts/${account.id}`);

},
});
Rationale: Fixture teardown runs even when the test fails, guaranteeing cleanup. afterEach is easier to forget.

5.5 Fixture Anti-Patterns
typescript
// ❌ BAD — fixture with assertions (hides failures)
loginPage: async ({ page }, use) => {
const loginPage = new LoginPage(page);
await loginPage.navigate();
await loginPage.login('user', 'pass');
await expect(page).toHaveURL('/dashboard'); // DON'T assert in fixture
await use(loginPage);
},

// ✅ GOOD — fixture only constructs
loginPage: async ({ page }, use) => {
await use(new LoginPage(page));
},

// ❌ BAD — fixture that mutates shared state
token: async ({ request }, use) => {
const { token } = await (await request.post('/auth')).json();
process.env.TOKEN = token; // DON'T mutate globals
await use(token);
},
SECTION 6 — HOOKS STANDARDS (beforeEach / afterEach / beforeAll / afterAll)
6.1 Hook Selection Matrix
Hook	Scope	Use For	Avoid For
beforeAll	Once per worker per file	Heavy one-time setup (DB seed)	Login, navigation, test-specific data
beforeEach	Before every test	Login, page navigation, per-test data	Shared mutable state
afterEach	After every test	Screenshots on failure, test-specific cleanup	Global cleanup
afterAll	Once per worker per file	Global cleanup (DB teardown)	Per-test data cleanup
6.2 Standard beforeEach Pattern
typescript
test.describe('Fund Transfer @banking', () => {
test.beforeEach(async ({ loginPage }) => {
// Authenticate before every test
await loginPage.navigate();
await loginPage.login(process.env.USER_EMAIL!, process.env.USER_PASSWORD!);
await loginPage.expectLoginSuccess();
});

test('transfers funds between own accounts', async ({ transferPage }) => {
// Already logged in — start testing
await transferPage.navigate();
await transferPage.transferFunds('12345', '67890', '100.00');
await transferPage.expectTransferSuccess();
});
});
When NOT to use beforeEach: If only 1 out of 10 tests needs login, put login inside that test or a nested test.describe with its own beforeEach.

6.3 Standard afterEach Pattern (Failure Artifacts)
typescript
// hooks/failure-artifacts.hook.ts
import { test as base } from '@playwright/test';

export const test = base.extend({
page: async ({ page }, use, testInfo) => {
await use(page);

    // Only on failure — attach screenshot and console logs
    if (testInfo.status !== testInfo.expectedStatus) {
      const screenshot = await page.screenshot({ fullPage: true });
      await testInfo.attach('failure-screenshot', {
        body: screenshot,
        contentType: 'image/png',
      });

      const logs = await page.evaluate(() =>
        JSON.stringify((window as any).__logs ?? [])
      );
      await testInfo.attach('browser-logs', {
        body: logs,
        contentType: 'application/json',
      });
    }

},
});
Rationale: Playwright config already captures trace/screenshot/video. This hook adds console logs and custom telemetry to Allure.

6.4 Data Cleanup afterEach Pattern
typescript
test.describe('Order Management @ecommerce', () => {
const createdOrderIds: string[] = [];

test.afterEach(async ({ apiClient }) => {
// Clean up orders created by this test
for (const id of createdOrderIds) {
await apiClient.delete(`/orders/${id}`);
}
createdOrderIds.length = 0;
});

test('places a new order', async ({ apiClient, checkoutPage }) => {
const order = await apiClient.post('/orders', { /* ... */ });
createdOrderIds.push(order.id);
// ... continue test
});
});
6.5 Hook Anti-Patterns
typescript
// ❌ BAD — beforeAll with UI login (state shared across workers incorrectly)
test.beforeAll(async ({ browser }) => {
const page = await browser.newPage();
await page.goto('/login'); // Runs once, but tests expect fresh session
});

// ❌ BAD — assertions in beforeEach
test.beforeEach(async ({ page }) => {
await page.goto('/dashboard');
await expect(page.getByText('Welcome')).toBeVisible(); // Hides root cause of failures
});

// ❌ BAD — afterEach that mutates global test data
test.afterEach(async () => {
delete globalTestUser; // Other workers may still need this
});

// ✅ GOOD — beforeEach uses fixtures, no assertions
test.beforeEach(async ({ loginPage }) => {
await loginPage.navigate();
await loginPage.login(process.env.USER_EMAIL!, process.env.USER_PASSWORD!);
});

// ✅ GOOD — afterEach handles only this file's data
test.afterEach(async ({ apiClient }) => {
await apiClient.delete(`/orders/${this.testOrderId}`);
});
SECTION 7 — TEST EXECUTION STANDARDS
7.1 Tagging Convention (Mandatory)
Every test MUST have at least one suite tag and one type tag.

Tag	Meaning	When to Run
@smoke	Critical path, < 5 min	Every PR, post-deploy
@sanity	Quick verification	After hotfix
@regression	Full functional coverage	Merge to main, nightly
@e2e	End-to-end user journeys	Nightly
@api	API-only tests	Every commit
@negative	Error handling	Nightly
@critical	Business-critical flows	Every deploy
@flaky	Known flaky (quarantined)	Manual only
@domain	e.g. @banking, @ecommerce	Subset runs
@module	e.g. @transfer, @checkout	Subset runs
Example:

typescript
test('transfers funds between accounts @smoke @regression @banking @transfer @critical', async () => { ... });
7.2 Test Execution Commands
bash

# Run all tests (headless)

npx playwright test

# Run smoke suite

npx playwright test --grep @smoke

# Run regression excluding flaky

npx playwright test --grep @regression --grep-invert @flaky

# Run specific domain

npx playwright test --grep @banking

# Run on specific browser

npx playwright test --project=chromium

# Run on all browsers (from config)

npx playwright test

# Run API tests only

npx playwright test --project=api

# Run headed (local debug)

npx playwright test --headed

# Run UI mode (interactive)

npx playwright test --ui

# Run with a specific worker count

npx playwright test --workers=4

# Run a single file

npx playwright test tests/smoke/login.smoke.spec.ts

# Run tests matching title pattern

npx playwright test -g "transfer funds"

# Run with sharding (4 shards, run shard 1)

npx playwright test --shard=1/4
7.3 Local Execution Standards
Scenario	Command
First-time local run	npm run test:install && npm run test:smoke
Debug a failing test	npx playwright test path/to/test.spec.ts --debug
Inspect trace	npx playwright show-trace test-results/.../trace.zip
Interactive test exploration	npx playwright test --ui
Run against staging	TEST_ENV=staging npm run test:smoke
Run only tests I touched	npx playwright test --only-changed
7.4 package.json Script Standard
json
{
"scripts": {
"test": "playwright test",
"test:smoke": "playwright test --grep @smoke",
"test:regression": "playwright test --grep @regression --grep-invert @flaky",
"test:sanity": "playwright test --grep @sanity",
"test:api": "playwright test --project=api",
"test:negative": "playwright test --grep @negative",
"test:headed": "playwright test --headed",
"test:ui": "playwright test --ui",
"test:debug": "playwright test --debug",
"test:chromium": "playwright test --project=chromium",
"test:firefox": "playwright test --project=firefox",
"test:webkit": "playwright test --project=webkit",
"test:ci": "playwright test --reporter=html,junit,allure-playwright",
"report:allure": "allure generate allure-results --clean -o allure-report && allure open allure-report",
"report:html": "playwright show-report",
"lint": "eslint . --ext .ts",
"lint:fix": "eslint . --ext .ts --fix",
"format": "prettier --write \"**/*.{ts,json,md}\"",
"typecheck": "tsc --noEmit",
"install:browsers": "playwright install --with-deps",
"clean": "rm -rf test-results playwright-report allure-results allure-report"
}
}
7.5 CI Execution Standards
yaml

# CI runs (in order, stop on failure of critical stages)

1. lint + typecheck (fast, fails build immediately)
2. API tests (@api) (fast, runs in parallel with smoke)
3. Smoke tests (@smoke) (blocks merge)
4. Regression (@regression) (post-merge)
5. Nightly full suite (all browsers, all tags)
   7.6 Parallel Execution Rules
   typescript
   // playwright.config.ts
   export default defineConfig({
   fullyParallel: true, // ✅ All tests run in parallel by default
   workers: process.env.CI ? 4 : undefined,
   retries: process.env.CI ? 2 : 0,
   });
   Rules for parallel safety:

Each test creates its own data (no shared IDs).

No test depends on another test's execution.

No file writes to shared paths (test-output.txt).

Auth via storageState file per role, created in setup project.

API-based setup preferred over UI (faster, isolated).

SECTION 8 — REVIEW CHECKLIST FOR PRs
Every PR that adds or modifies tests MUST pass this checklist. Add it to .github/pull_request_template.md.

markdown

## Page Object Review

- [ ] File follows naming: `kebab-case.page.ts`
- [ ] Extends `BasePage`
- [ ] Locators are `readonly` class properties
- [ ] Locators use `getByRole` / `getByLabel` / `getByTestId`
- [ ] No CSS, no XPath, no `nth-child`
- [ ] Constructor only assigns locators (no async)
- [ ] Action methods are atomic (one interaction each)
- [ ] Business methods compose atomic methods
- [ ] Self-validating `expect*()` methods provided
- [ ] No `page.waitForTimeout()` used

## Test File Review

- [ ] File follows naming: `feature.type.spec.ts`
- [ ] Suite has domain tags (`@banking`, `@ecommerce`)
- [ ] Test name follows `<actor> <action> <outcome>` pattern
- [ ] At least one type tag (`@smoke`, `@regression`, `@api`, `@negative`)
- [ ] Uses fixtures (no `new Page(page)` inside test)
- [ ] AAA structure (Arrange-Act-Assert)
- [ ] No conditional logic inside tests
- [ ] No hardcoded test data (uses factories/fixtures)
- [ ] Assertions validate business outcomes
- [ ] `test.step()` used for complex flows (>3 interactions)

## Fixtures Review

- [ ] Fixtures do not assert (no `expect()` inside fixture setup)
- [ ] Fixtures with side effects provide teardown via `await use()`
- [ ] Worker-scoped fixtures used only for expensive resources
- [ ] No global state mutation inside fixtures

## Hooks Review

- [ ] `beforeEach` has no assertions that would hide root cause
- [ ] `afterEach` cleans up data created in this file only
- [ ] `beforeAll` / `afterAll` used only for worker-scoped resources
- [ ] No shared mutable state across tests

## Execution Review

- [ ] Test runs locally: `npx playwright test <path>`
- [ ] Test runs in CI (chromium at minimum)
- [ ] Test is parallel-safe (creates own data)
- [ ] Trace/screenshot captured on failure
- [ ] Allure report shows meaningful steps
- [ ] No new flakiness introduced (run 3× consecutively)
      SECTION 9 — COMPLETE RUNNABLE EXAMPLE
      Here's a production-ready mini framework showing all standards in action.

Folder layout
text
project/
├── pages/
│ ├── base.page.ts
│ └── login.page.ts
│ └── transfer.page.ts
├── fixtures/
│ └── app.fixtures.ts
├── utils/
│ └── data-factory.ts
├── tests/
│ └── smoke/
│ └── transfer.smoke.spec.ts
├── playwright.config.ts
└── package.json
pages/base.page.ts
typescript
import { Page, expect } from '@playwright/test';

export abstract class BasePage {
constructor(readonly page: Page) {}
abstract readonly url: string;

async navigate() {
await this.page.goto(this.url);
await this.page.waitForLoadState('domcontentloaded');
}

async getToast(): Promise<string> {
const toast = this.page.getByRole('alert');
await expect(toast).toBeVisible();
return (await toast.textContent()) ?? '';
}
}
pages/login.page.ts
typescript
import { Page, Locator, expect } from '@playwright/test';
import { BasePage } from './base.page';

export class LoginPage extends BasePage {
readonly url = '/login';
readonly emailInput: Locator;
readonly passwordInput: Locator;
readonly submitButton: Locator;
readonly errorBanner: Locator;
readonly dashboardHeading: Locator;

constructor(page: Page) {
super(page);
this.emailInput = page.getByLabel('Email');
this.passwordInput = page.getByLabel('Password');
this.submitButton = page.getByRole('button', { name: 'Sign In' });
this.errorBanner = page.getByTestId('login-error');
this.dashboardHeading = page.getByRole('heading', { name: /Dashboard/ });
}

async login(email: string, password: string) {
await this.emailInput.fill(email);
await this.passwordInput.fill(password);
await this.submitButton.click();
}

async expectLoginSuccess() {
await expect(this.dashboardHeading).toBeVisible();
}

async expectLoginError(message: string) {
await expect(this.errorBanner).toContainText(message);
}
}
pages/transfer.page.ts
typescript
import { Page, Locator, expect } from '@playwright/test';
import { BasePage } from './base.page';

export class TransferPage extends BasePage {
readonly url = '/transfer';
readonly fromAccount: Locator;
readonly toAccount: Locator;
readonly amountInput: Locator;
readonly submitButton: Locator;
readonly successBanner: Locator;
readonly errorBanner: Locator;

constructor(page: Page) {
super(page);
this.fromAccount = page.getByLabel('From Account');
this.toAccount = page.getByLabel('To Account');
this.amountInput = page.getByLabel('Amount');
this.submitButton = page.getByRole('button', { name: 'Transfer' });
this.successBanner = page.getByTestId('transfer-success');
this.errorBanner = page.getByTestId('transfer-error');
}

async transferFunds(from: string, to: string, amount: string) {
await this.fromAccount.selectOption(from);
await this.toAccount.selectOption(to);
await this.amountInput.fill(amount);
await this.submitButton.click();
}

async expectSuccess() {
await expect(this.successBanner).toBeVisible();
}

async expectError(message: string) {
await expect(this.errorBanner).toContainText(message);
}
}
fixtures/app.fixtures.ts
typescript
import { test as base, expect } from '@playwright/test';
import { LoginPage } from '@pages/login.page';
import { TransferPage } from '@pages/transfer.page';

type AppFixtures = {
loginPage: LoginPage;
transferPage: TransferPage;
};

export const test = base.extend<AppFixtures>({
loginPage: async ({ page }, use) => await use(new LoginPage(page)),
transferPage: async ({ page }, use) => await use(new TransferPage(page)),
});

export { expect };
tests/smoke/transfer.smoke.spec.ts
typescript
import { test, expect } from '@fixtures/app.fixtures';

test.describe('Fund Transfer @smoke @banking @transfer', () => {
test.beforeEach(async ({ loginPage }) => {
await loginPage.navigate();
await loginPage.login(process.env.USER_EMAIL!, process.env.USER_PASSWORD!);
await loginPage.expectLoginSuccess();
});

test('user transfers funds between own accounts @critical', async ({ transferPage }) => {
await test.step('Navigate to transfer page', async () => {
await transferPage.navigate();
});

    await test.step('Execute transfer', async () => {
      await transferPage.transferFunds('ACC-12345', 'ACC-67890', '100.00');
    });

    await test.step('Verify success', async () => {
      await transferPage.expectSuccess();
    });

});

test('transfer fails with insufficient funds @negative', async ({ transferPage }) => {
await transferPage.navigate();
await transferPage.transferFunds('ACC-12345', 'ACC-67890', '9999999');
await transferPage.expectError('Insufficient funds');
});
});
Running the tests
bash

# 1. Install

npm ci
npx playwright install --with-deps chromium

# 2. Configure environment

cp .env.example .env

# Fill BASE_URL, USER_EMAIL, USER_PASSWORD

# 3. Run smoke

npx playwright test --grep @smoke

# 4. View HTML report

npx playwright show-report

# 5. Generate Allure

allure generate allure-results --clean -o allure-report
allure open allure-report
SECTION 10 — STANDARDS AT A GLANCE (Cheat Sheet)
Layer	Rule
Page Object	One per page, readonly locators, extends BasePage, getBy* only
Component	Reusable UI fragments (header, table, modal)
Test file	feature.type.spec.ts, tagged, AAA, no locators inline
Test name<actor> <action> <outcome> + tags
Fixture	Injects Page Objects & API clients; no assertions inside
beforeEach	Login, navigation, per-test setup (no assertions)
afterEach	Failure artifacts, data cleanup for this file only
beforeAll/afterAll	Worker-scoped resources only (DB seed, token)
Assertions	Auto-waiting expect().toBeVisible(); validate business outcomes
Data	Faker / API-created / unique per test
Tags	At least one domain + one type tag per test
Parallelism	Each test isolated; no shared mutable state
CI	Smoke on PR, regression on merge, nightly full suite
Reports	Allure + HTML + JUnit; steps for readability
SECTION 11 — QUICK REFERENCE: WHEN TO USE WHAT
Need	Use
Locate an element	getByRole > getByLabel > getByTestId
Assert an outcome	await expect(...).toBeVisible()
Create test data	Factory function with Faker
Set up an account	API call in fixture or helper
Login before test	storageState (config) or beforeEach
Clean up data	Fixture teardown or afterEach
Inject a Page Object	Custom fixture
One-time setup	globalSetup
Global teardown	globalTeardown
Complex flow	test.step()
Split suite for CI	--shard=N/M
Skip a flaky test	test.fixme() or test.skip(condition)
Data-driven test	Loop in test.describe or parameterized array
Cross-browser	Multiple projects in config
Debug locally	--debug or --ui
Inspect a failure	Trace Viewer (show-trace)
This document is your team's source of truth for how tests are written, reviewed, and executed. Enforce it via:

PR template (Section 8) — every PR must check the boxes.

ESLint rules — custom rules for banned patterns like waitForTimeout.

Pre-commit hooks — run lint, typecheck, and affected tests before push.

Code review — Senior SDET approves every PR adding tests.

Once enforced, junior engineers will naturally write production-quality tests, and your framework will scale without accumulating technical debt.
