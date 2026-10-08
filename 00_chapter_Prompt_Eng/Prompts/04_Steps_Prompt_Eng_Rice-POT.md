# Steps to Follow for Effective Prompt Engineering

1. | Define the Goal │
2. │ Gather Context │
3. │ Choose Prompting Strategy │
4. │ Structure the Prompt │
5. │ Add Constraints │
6. │ Test and Iterate │
7. │ Document and Reuse - Skill (publish)

## Step 1: Define the Goal

**Ask yourself:**

- What exactly do I need?
- What will I do with the output?
- What does success look like?
  ❌ Vague: "Help me with testing"
  ✅ Clear: "Generate 10 test cases for login validation"

## Step 2: Gather Context

**Collect all relevant information:**

- [ ] PRD / Requirements document
- [ ] API documentation
- [ ] Screenshots / UI mockups
- [ ] Error logs
- [ ] Previous test cases
- [ ] Constraints / Limitations
      **Rule:** More context = Better output

## Step 3: Choose Prompting Strategy

| <u>**Situation**</u>                                                                          | <u>**Strategy**</u>     |                           |
| --------------------------------------------------------------------------------------------- | ----------------------- | ------------------------- |
| <u>Simple, standard task</u>                                                                  | <u>Zero-Shot</u>        | No Example                |
| <u>Custom format needed</u>                                                                   | <u>Few-Shot</u>         | 2-3 examples              |
| <u>Complex analysis</u>                                                                       | <u>Chain-of-Thought</u> | Ask him to think about it |
| <u>Domain expertise needed</u>                                                                | <u>Role-Based</u>       | Act as QA                 |
| **Zero shot ->.** in the zero-shot, you have a very simple task that you do without examples. |                         |                           |

**Few Shot -> (QA) :** you have to use a few examples if you have a little complex or custom-formatted task that you want

Create a bug report for [app.vwo.com](https://app.vwo.com/) -> title where the login page is not working.

**Example**

A Jira bug report will contain:

- our scenario ID - 1
- scenario test case ID - 001
- test case title - verify this app.log in.com is working.
- test case description
- expected result
- output results
- comment severity
- priority
  Result ->

Result ->

**Bug Title:** Login page is not working on app.vwo.com

**Scenario ID:** 1

**Scenario Test Case ID:** 001

**Test Case Title:** Verify that the login page of app.vwo.com is working.

**Test Case Description:**
Verify that a user is able to access the login page, enter valid login credentials, and successfully log in to app.vwo.com without any errors.

**Steps to Reproduce:**

1. Open the browser.
2. Navigate to [https://app.vwo.com](https://app.vwo.com/) .
3. Enter valid email ID and password.
4. Click on the **Login** button.
5. Observe the result.
   **Expected Result:**
   The user should be successfully logged in and redirected to the VWO dashboard/home page.

**Actual Result / Output Result:**
The login page is not working. The user is unable to log in to app.vwo.com. The page may not respond, show an error, or fail to redirect after clicking the Login button.

**Severity:** High

**Priority:** High

**Comment:**
This issue blocks users from accessing the application. Since login is a critical functionality, it should be fixed as soon as possible.

## Step 4: Structure the Prompt

> 10-15 Frameworks

**Use a framework (RICE POT recommended):**

## The RICE-POT Prompt Framework

### **RICE-POT Breakdown**

RICE-POT is a useful framework for converting a vague request into a structured, reusable prompt. I checked the term because it is a relatively niche prompting framework; the common definition is Role, Instructions, Context, Example, Parameters, Output, Tone.

One important point: Objective/Goal sits above RICE-POT. It is not one of the seven letters, but it should be defined first because it drives the rest of the prompt.

RICE-POT Prompt Framework

Think of it as:

OBJECTIVE
↓
R — Role
I — Instructions
C — Context
E — Example
P — Parameters
O — Output
T — Tone
R — Role
What it means

Tell the AI who it should behave like.

This gives the model an expertise/context boundary.

Weak

Create a Playwright framework.

Better

Act as a Senior SDET with expertise in Playwright and TypeScript.

Stronger for your use case

Act as a Principal SDET, Automation Architect, and QA Technical Lead with extensive experience designing enterprise Playwright + TypeScript automation frameworks for banking, financial services, e-commerce, SaaS, and other modern web applications.

Why it matters

The role can influence:

technical depth
architecture decisions
terminology
best practices
level of explanation
trade-off analysis
Example for your project
R — Role

You are a Principal SDET and Automation Architect specializing in:

- Playwright
- TypeScript
- JavaScript
- API automation
- CI/CD
- Azure DevOps
- GitHub
- GitHub Copilot
- Playwright MCP
- enterprise test automation
- banking and financial applications
- e-commerce applications

Think like an engineer responsible for building a framework that
will be maintained by a 5–20 member automation team.
I — Instructions
What it means

Tell the AI exactly what to do.

This is usually the largest part of the prompt.

Don't just say:

Build a framework.

Instead define the work in stages.

Example
I — Instructions

1. Analyze the application requirements.
2. Identify automation scope.
3. Define the test strategy.
4. Design the framework architecture.
5. Create the folder structure.
6. Configure Playwright and TypeScript.
7. Implement Page Object Model.
8. Implement reusable components.
9. Implement fixtures.
10. Implement API utilities.
11. Implement authentication.
12. Implement test-data management.
13. Implement reporting.
14. Implement CI/CD.
15. Integrate GitHub and Azure DevOps.
16. Explain how GitHub Copilot and MCP can assist.
17. Add debugging and maintenance strategy.
18. Validate the framework against enterprise standards.
    Add "Do" and "Don't"

This is extremely useful.

DO:

- Use TypeScript.
- Prefer Playwright web-first assertions.
- Use stable locators.
- Keep tests independent.
- Use reusable components.
- Keep secrets outside source code.

DON'T:

- Use hard waits unnecessarily.
- Hardcode credentials.
- Duplicate locators.
- Put business logic directly into tests.
- Invent APIs or requirements.
- Add libraries without explaining why.

This aligns with the recommendation that strong prompts should state constraints and anti-hallucination rules explicitly.

C — Context
What it means

Give the AI the background information it needs.

Without context, the model has to guess.

Example
C — Context

Application:
Enterprise Banking Web Application

Frontend:
React

Backend:
.NET REST APIs

Automation:
Playwright + TypeScript

Authentication:
SSO + MFA

Browsers:
Chrome, Edge, Firefox

Environments:
QA, UAT, Production

CI/CD:
Azure DevOps

Repository:
GitHub

Reporting:
Playwright HTML + Allure

Team:
8 SDETs

Approximate automation:
2,000 tests

Architecture:
POM + Fixtures + API + Components
Context can include actual project material

You can attach:

PRD
Jira user stories
API specifications
screenshots
existing code
package.json
playwright.config.ts
architecture diagrams
existing test cases

The more relevant context you provide, the less the AI needs to assume.

E — Example
What it means

Show the AI what good output looks like.

This is particularly powerful when you care about a specific format or coding style.

It is essentially a few-shot/example-driven technique.

Example

Suppose you want Page Objects written in a particular way.

Give the agent:

export class LoginPage {
constructor(private page: Page) {}

readonly username = this.page.getByLabel('Username');
readonly password = this.page.getByLabel('Password');
readonly loginButton = this.page.getByRole('button', {
name: 'Login'
});

async login(user: string, pass: string) {
await this.username.fill(user);
await this.password.fill(pass);
await this.loginButton.click();
}
}

Then say:

Use this coding style for all Page Object classes.

Now the AI has a concrete reference.

You can also provide output examples

For a test:

test('valid login', async ({ page }) => {
const loginPage = new LoginPage(page);

await loginPage.login(
process.env.USERNAME!,
process.env.PASSWORD!
);

await expect(page).toHaveURL(/dashboard/);
});

Then request:

Create similar tests for fund transfer, beneficiary management, and account statements.

This usually produces more consistent results than simply describing your preferred style.

P — Parameters
What it means

Parameters define the boundaries and quality requirements.

Think:

How much? Which technology? What limits? What quality?

Example
P — Parameters

Technology:

- Playwright Test
- TypeScript
- Node.js
- GitHub
- Azure DevOps

Architecture:

- Page Object Model
- Component Objects
- Fixtures
- API utilities

Browsers:

- Chromium
- Firefox
- WebKit

Quality:

- Maintainable
- Scalable
- Reusable
- Secure
- CI/CD ready

Execution:

- Parallel execution
- Retry on CI
- Trace on first retry

Testing:

- Smoke
- Regression
- Functional
- Negative
- API
- E2E

Parameters can also specify quantities:

Generate 10 critical smoke tests.

Create 5 negative tests for each high-risk transaction.

Support 3 environments.

Use a maximum of 3 external npm dependencies unless justified.
Quality parameters

You can even specify:

Every design decision must include:

Why it is needed
Advantages
Disadvantages
Alternative approach

This is particularly good for your Architect-level learning.

O — Output
What it means

Tell the AI exactly what you want it to return.

This is one of the most overlooked parts of prompting.

Weak

Explain the framework.

Better
O — Output

Provide the response in this order:

1. Executive summary
2. Architecture
3. Folder structure
4. Installation commands
5. Configuration
6. Page Object Model
7. Fixtures
8. Utilities
9. API automation
10. Authentication
11. Test examples
12. Reporting
13. Git workflow
14. CI/CD
15. Azure DevOps
16. GitHub Copilot
17. MCP
18. Troubleshooting
19. Best practices
20. Final checklist

You can specify format too:

Use Markdown headings, tables, Mermaid architecture diagrams, and TypeScript code blocks.

Or:

Return only the requested code files.

Very important

Don't mix contradictory output instructions.

For example:

"Explain everything in detail."

and:

"Output only code."

Those conflict.

A good prompt should have one clear output contract. This is also called out in guidance on RICE-POT prompting.

T — Tone
What it means

Tell the AI how it should communicate.

Examples:

Technical
Professional
Beginner-friendly
Enterprise architect style
Concise
Detailed
Coaching
Interview-oriented
Example for your learning system
T — Tone

Use a professional Senior SDET / Automation Architect tone.

Explain concepts in simple language first, then provide technical depth.

Be practical rather than theoretical.

Use real enterprise examples.

When there are multiple possible approaches, recommend one
and explain why.

Do not use unnecessarily complex terminology.

For interviews, you might instead use:

T — Tone

Explain the topic in a way suitable for a Senior SDET interview.

Start with a simple explanation, then provide an
experienced-engineer answer and a practical example.
Complete RICE-POT example for your Playwright goal

Here's what your framework prompt could look like when assembled.

RICE-POT Prompt — Enterprise Playwright SDET Skill System
OBJECTIVE

Build a reusable enterprise-level knowledge and automation framework blueprint that helps me design, implement, maintain, and scale modern web automation using Playwright + TypeScript.

The solution must be reusable across banking, financial services, FinTech, e-commerce, SaaS, healthcare, and other enterprise web applications.

The end goal is to develop Senior SDET / Automation Architect-level skills rather than simply learning Playwright syntax.

R — ROLE

Act as a Principal SDET, Automation Architect, QA Technical Lead, and AI-Assisted Test Automation Expert with extensive enterprise experience in:

Playwright
TypeScript
JavaScript
UI automation
API automation
test architecture
Page Object Model
fixtures
authentication
test-data management
GitHub
Azure DevOps
CI/CD
GitHub Copilot
Playwright Codegen
MCP-based automation
enterprise testing practices

Think like an engineer responsible for a framework used by a 5–20 member automation team across multiple enterprise applications.

I — INSTRUCTIONS

Build the solution progressively from beginner to enterprise level.

Cover:

Requirement analysis
Test strategy
Automation scope
Project setup
Node.js and TypeScript
Playwright installation
Configuration
Environment management
Page Object Model
Component architecture
Fixtures
Test hooks
Utilities
API automation
Authentication
Test-data management
UI testing
API + UI integration
Codegen
GitHub Copilot
MCP and AI-assisted automation
Azure DevOps
Git workflow
Reporting
Debugging
Flaky-test management
CI/CD
Parallel execution
Test scalability
Framework maintenance
Team management
Enterprise governance

For every major topic:

Explain what it is.
Explain why it is needed.
Explain where it is used.
Provide a practical example.
Provide production-oriented code where appropriate.
Explain common mistakes.
Explain enterprise best practices.
Explain alternatives and trade-offs.

Do not invent missing application requirements. Clearly identify assumptions.

C — CONTEXT

The framework is intended for modern enterprise applications such as:

Banking
Financial services
FinTech
Insurance
E-commerce
SaaS
Healthcare

Typical technology stack:

Frontend: React / Angular / Vue
Backend: REST APIs
Automation: Playwright + TypeScript
Repository: GitHub
CI/CD: GitHub Actions or Azure DevOps
Reporting: Playwright HTML / Allure
Test management: Azure DevOps
AI assistance: GitHub Copilot
MCP: Playwright MCP and relevant enterprise integrations

The framework must support:

QA
UAT
Staging
Production-safe validation where appropriate
Multiple browsers
Multiple environments
Multiple user roles
UI + API testing
E — EXAMPLE

Use the following coding style as a reference:

export class LoginPage {
constructor(private page: Page) {}

readonly username = this.page.getByLabel('Username');
readonly password = this.page.getByLabel('Password');
readonly loginButton = this.page.getByRole('button', {
name: 'Login'
});

async login(user: string, pass: string) {
await this.username.fill(user);
await this.password.fill(pass);
await this.loginButton.click();
}
}

Follow this general style when creating Page Objects:

constructor injection
stable Playwright locators
reusable business methods
clear TypeScript types
no hard-coded credentials
no unnecessary waits

When no example is provided, select a modern Playwright best practice and explain the choice.

P — PARAMETERS

Technology constraints:

TypeScript
Playwright Test
Node.js
Git
GitHub
Azure DevOps

Architecture requirements:

Page Object Model
Component Objects where appropriate
Fixtures
Reusable utilities
API layer
Configuration layer
Test-data layer

Quality requirements:

Maintainable
Scalable
Secure
Reusable
CI/CD ready
Cross-browser capable
Parallel-execution capable

Testing requirements:

Smoke
Sanity
Functional
Regression
Negative
API
End-to-end
Cross-browser

Coding rules:

Prefer Playwright web-first assertions.
Prefer stable locators such as role, label, and test ID.
Avoid unnecessary hard waits.
Keep tests independent.
Do not duplicate locators or business logic.
Do not hard-code secrets.
Do not invent dependencies or APIs.
Explain any additional dependency before introducing it.

For architectural decisions, provide:

Recommended approach
Why it is recommended
Advantages
Limitations
Alternative approach
O — OUTPUT

Organize the final solution into:

Executive overview
Learning roadmap
Enterprise architecture
Project setup from scratch
Folder structure
Playwright configuration
Page Object Model
Components
Fixtures
Test scripts
Test data
API automation
Authentication
Utilities
Reporting
Git strategy
GitHub workflow
Azure DevOps integration
Codegen
GitHub Copilot
MCP
CI/CD
Debugging
Flaky-test management
Scalability
Team management
Governance
Troubleshooting
Interview questions
Reusable enterprise checklist

Use:

Markdown headings
tables where useful
TypeScript code blocks
folder-tree diagrams
Mermaid diagrams where useful
practical enterprise examples

For large implementation tasks, work in clearly numbered phases rather than generating everything as one unstructured response.

T — TONE

Use a professional Principal SDET / Automation Architect tone.

Explain complex concepts in simple language first, then provide technical depth.

Be practical and enterprise-focused.

Teach the reasoning behind the solution rather than only providing code.

When multiple approaches exist, recommend one and explain why.

Avoid unnecessary jargon and avoid over-engineering.

The final material should be suitable as a long-term SDET learning and enterprise automation reference.

The key idea

For your AI + Playwright + SDET learning goal, RICE-POT should not be thought of as merely a prompt-writing trick.

It's better viewed as a prompt specification:

Component	Question it answers
Objective	What am I trying to achieve?
R — Role	Who should the AI behave as?
I — Instructions	What exactly should it do?
C — Context	What information does it need?
E — Example	What does good output look like?
P — Parameters	What rules, limits, and quality standards apply?
O — Output	Exactly what should it produce?
T — Tone	How should it communicate?

The biggest lesson is don't rely on Role alone. A prompt saying "You are a 15-year-experienced SDET" does not guarantee expert output. The context, examples, constraints, output contract, and evaluation criteria are what make the prompt much more reliable. The RICE-POT material I found also emphasizes checking the generated result against the original prompt rather than assuming the model followed every instruction.
