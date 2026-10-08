You are a Principal/Staff-level SDET and Test Automation Architect with 15+ years of hands-on experience designing, implementing, maintaining, and leading enterprise automation solutions using Playwright, TypeScript, JavaScript, API automation, GitHub Copilot, Playwright Codegen, MCP servers, Azure DevOps, GitHub Actions, and modern CI/CD practices.

Your objective is to create a complete, enterprise-grade, reusable Playwright + TypeScript automation engineering skill and knowledge framework that I can customize for different modern web applications.

The framework must be applicable to large-scale applications in domains such as:

- Banking
- Financial services
- FinTech
- Insurance
- E-commerce
- Retail
- Healthcare
- SaaS
- Enterprise business applications

The goal is not only to teach Playwright syntax, but to teach me how an experienced SDET/Automation Architect thinks, designs, develops, reviews, executes, maintains, and scales automation in a real enterprise project.

1. Teaching Approach

Teach everything from beginner → intermediate → advanced → expert/enterprise level.

Do not skip important concepts.

For every major topic:

1. Explain what it is.
2. Explain why it is required.
3. Explain where it is used in a real project.
4. Show the recommended enterprise approach.
5. Provide a practical example.
6. Explain common mistakes.
7. Explain best practices.
8. Explain how the approach changes for large-scale applications.
9. Provide interview-level questions where appropriate.

Use simple language first, followed by technical depth.

Whenever possible, explain the reasoning behind architectural decisions instead of simply providing code.

---

PART 1 — ENTERPRISE AUTOMATION ENGINEERING FUNDAMENTALS

Explain the responsibilities of:

- QA Engineer
- Automation Test Engineer
- SDET
- Senior SDET
- Automation Architect
- Test Lead
- QA Lead

Explain how an enterprise automation team works.

Cover:

- Requirement analysis
- Test strategy
- Test planning
- Test estimation
- Test case design
- Automation feasibility analysis
- Automation candidate selection
- Framework design
- Development
- Code review
- Test execution
- Defect management
- Reporting
- CI/CD
- Maintenance
- Release validation
- Production validation
- Automation metrics

Explain how developers, QA, product owners, business analysts, DevOps, architects, and managers collaborate.

---

PART 2 — DOMAIN-SPECIFIC TESTING KNOWLEDGE

Create reusable testing knowledge for:

Banking

Cover examples such as:

- Login/authentication
- MFA/OTP
- Account management
- Balance
- Fund transfer
- Beneficiaries
- Payments
- Statements
- Transaction history
- Cards
- Loans
- Fraud/security scenarios
- Session timeout
- Authorization
- Role-based access
- Audit trails

Financial Services / FinTech

Cover:

- KYC
- Customer onboarding
- Payments
- Transactions
- Investment workflows
- Portfolio
- Trading-related workflows
- Financial calculations
- Data validation
- Compliance
- Auditability
- Security

E-commerce

Cover:

- Registration/login
- Product search
- Filters
- Product details
- Cart
- Wishlist
- Coupons
- Checkout
- Payments
- Order confirmation
- Order tracking
- Returns/refunds
- Inventory
- Customer profile

For every domain, explain:

- Functional testing
- UI testing
- API testing
- Integration testing
- End-to-end testing
- Regression testing
- Smoke testing
- Sanity testing
- Negative testing
- Boundary testing
- Security-related testing
- Data validation
- Cross-browser testing

Make the domain scenarios reusable so I can replace the business workflows with those of another application.

---

PART 3 — TEST AUTOMATION STRATEGY

Explain how to decide:

- What should be automated?
- What should not be automated?
- UI vs API automation
- End-to-end vs component-level testing
- Smoke vs regression automation
- Risk-based automation
- Test pyramid
- Test stability
- Flaky test management
- Automation ROI
- Maintenance cost

Create an example automation strategy for an enterprise web application.

---

PART 4 — PLAYWRIGHT FROM SCRATCH

Teach Playwright + TypeScript from zero.

Cover:

- Node.js
- npm
- TypeScript
- Playwright
- VS Code
- Git
- GitHub
- Playwright Test

Explain how to install everything from scratch.

Provide:

- Prerequisites
- Installation commands
- Project initialization
- Directory structure
- Configuration
- Browser installation
- First test
- Test execution
- Debugging
- Headed/headless execution
- Trace viewer
- Screenshots
- Videos
- Test reports

Use current stable practices rather than outdated approaches.

---

PART 5 — ENTERPRISE PLAYWRIGHT PROJECT STRUCTURE

Design a scalable folder structure such as:

automation-project/
│
├── tests/
├── pages/
├── components/
├── fixtures/
├── utils/
├── helpers/
├── api/
├── test-data/
├── schemas/
├── constants/
├── config/
├── hooks/
├── types/
├── reporters/
├── scripts/
├── playwright.config.ts
├── package.json
├── tsconfig.json
├── .env
├── .gitignore
└── README.md

Explain every folder and when it should be used.

Show how the structure should evolve from:

Beginner → Small project → Medium project → Enterprise project.

---

PART 6 — PLAYWRIGHT CONFIGURATION

Explain "playwright.config.ts" in detail.

Cover:

- testDir
- timeout
- expect timeout
- retries
- workers
- projects
- browsers
- baseURL
- reporter
- screenshot
- video
- trace
- storageState
- authentication
- global setup
- global teardown
- webServer
- environment variables
- CI-specific configuration

Explain how to create configurations for:

- Local
- QA
- UAT
- Staging
- Production

Explain environment-specific configuration without hardcoding credentials.

---

PART 7 — PAGE OBJECT MODEL

Teach POM from beginner to enterprise level.

Cover:

- Page classes
- Locators
- Page methods
- Reusable components
- Base page
- Base component
- Assertions
- Navigation methods
- Business methods

Explain:

Bad POM vs Good POM vs Enterprise POM.

Provide examples for:

- Login page
- Dashboard
- Search page
- Product page
- Checkout
- Banking transaction page

Explain when POM should NOT be used and when component objects or other abstractions are better.

---

PART 8 — LOCATOR STRATEGY

Teach reliable locator design.

Cover:

- getByRole
- getByLabel
- getByText
- getByPlaceholder
- getByTestId
- CSS
- XPath

Explain:

- Locator priority
- Dynamic elements
- Shadow DOM
- Frames
- Multiple matching elements
- Strict mode
- Dynamic IDs
- Tables
- Dropdowns
- Calendars
- Modals
- Toast messages
- Virtualized lists

Explain how to create stable locators for enterprise applications.

---

PART 9 — TEST SCRIPT DESIGN

Show how to write maintainable tests.

Cover:

- Test structure
- Arrange/Act/Assert
- Hooks
- beforeEach
- beforeAll
- afterEach
- afterAll
- Fixtures
- Test data
- Assertions
- Tags
- Annotations
- Dependencies
- Parallel execution

Show examples for:

- Smoke
- Regression
- Functional
- Negative
- Data-driven
- Parameterized
- End-to-end
- Cross-browser tests

---

PART 10 — TEST DATA MANAGEMENT

Explain enterprise test-data strategies.

Cover:

- JSON
- CSV
- Excel
- Environment variables
- Database data
- API-generated data
- Faker
- Dynamic test data
- Test-data cleanup
- Sensitive data
- Secrets

Explain how to prevent test data from becoming a maintenance problem.

---

PART 11 — API AUTOMATION

Teach API testing using Playwright APIRequestContext and other appropriate tools.

Cover:

- GET
- POST
- PUT
- PATCH
- DELETE
- Headers
- Authentication
- Tokens
- Cookies
- Request payloads
- Response validation
- JSON schema validation
- Status codes
- Contract validation
- Chained API calls
- API + UI testing
- Test data setup through APIs

Show an enterprise API + UI hybrid framework.

---

PART 12 — AUTHENTICATION AND SECURITY

Explain enterprise authentication automation.

Cover:

- Username/password
- OAuth
- JWT
- SSO
- MFA/OTP
- Cookies
- Storage state
- Session management
- Token management
- Role-based access
- Admin/user roles
- Session timeout
- Unauthorized access

Explain how to handle authentication securely without committing credentials to Git.

---

PART 13 — CODEGEN

Teach Playwright Codegen.

Explain:

- What Codegen is
- How to start it
- Recording workflows
- Generated locators
- Generated code
- What Codegen does well
- What Codegen does poorly
- How to refactor Codegen output into production-quality automation

Show:

Codegen → Review → Refactor → POM → Test → CI/CD.

---

PART 14 — GITHUB COPILOT

Explain how an experienced SDET can use GitHub Copilot effectively for automation.

Cover:

- Test generation
- Locator generation
- POM generation
- Refactoring
- Debugging
- Code explanation
- Test-data generation
- API test generation
- Documentation
- Code review
- Unit/helper function generation

Explain how to write effective prompts for Copilot.

Also explain what code should NOT blindly be accepted from AI.

---

PART 15 — MCP SERVERS AND AI-ASSISTED AUTOMATION

Explain MCP from beginner to advanced level.

Cover:

- What MCP is
- MCP architecture
- MCP client
- MCP server
- Tools
- Resources
- Prompts
- Context
- How AI interacts with tools

Explain practical automation use cases involving:

- Playwright MCP
- Azure DevOps MCP
- GitHub MCP
- Other relevant enterprise MCP integrations

Show example workflows such as:

Requirement → AI analysis → Test design → Playwright implementation → Test execution → Result analysis → Azure DevOps update.

Explain the security risks and governance requirements of MCP in enterprise environments.

Do not assume that an MCP server automatically makes automation reliable. Explain human review and validation.

---

PART 16 — AZURE DEVOPS

Explain how automation integrates with Azure DevOps.

Cover:

- Boards
- Work items
- Test cases
- Repositories
- Pipelines
- Artifacts
- Test results
- Bug reporting
- Traceability

Show an example workflow:

Requirement → User Story → Test Case → Automation → Commit → Pipeline → Test Result → Bug → Dashboard.

Explain how MCP/AI can assist without bypassing enterprise controls.

---

PART 17 — REPORTING

Explain enterprise test reporting.

Cover:

- HTML reports
- Playwright reports
- Allure
- JUnit
- CI reports
- Screenshots
- Videos
- Traces
- Logs

Explain what a good automation report should contain.

Also explain:

- Failure analysis
- Flaky tests
- Execution trends
- Pass/fail percentage
- Duration
- Failure categorization

---

PART 18 — CI/CD

Teach CI/CD integration from scratch.

Cover:

- Git
- GitHub
- GitHub Actions
- Azure DevOps Pipelines

Explain:

Developer → Branch → Commit → Pull Request → Code Review → Pipeline → Install dependencies → Browser installation → Test execution → Reports → Artifacts → Notification.

Provide example YAML pipelines.

Cover:

- Environment variables
- Secrets
- Parallel execution
- Sharding
- Retries
- Caching
- Browser installation
- Artifacts
- Reports
- Scheduled execution
- Manual execution
- Smoke pipeline
- Regression pipeline

---

PART 19 — GIT AND TEAM WORKFLOW

Explain enterprise Git practices.

Cover:

- Branching strategy
- Feature branches
- Main/master
- Develop
- Pull requests
- Code reviews
- Merge conflicts
- Rebase
- Cherry-pick
- Tags
- Releases
- Commit standards

Show how a 4–10 member automation team can work on the same framework without constantly breaking each other's code.

---

PART 20 — TEAM MANAGEMENT

Teach me how a Senior SDET/Lead manages an automation team.

Cover:

- Task allocation
- Sprint planning
- Estimation
- Automation roadmap
- Code reviews
- Standards
- Mentoring
- Training junior engineers
- Technical discussions
- Defect triage
- Framework ownership
- Automation metrics
- Release readiness

Provide examples of:

- Sprint plan
- Automation task breakdown
- Definition of Done
- Coding standards
- Review checklist

---

PART 21 — ENTERPRISE QUALITY ENGINEERING

Explain advanced engineering practices:

- SOLID principles
- DRY
- KISS
- Clean code
- Design patterns
- Dependency management
- Reusable utilities
- Logging
- Error handling
- Configuration management
- Observability
- Test isolation
- Parallel execution
- Scalability
- Maintainability

Explain which design patterns are actually useful in Playwright and which are unnecessary over-engineering.

---

PART 22 — FLAKY TEST MANAGEMENT

Explain how to identify and eliminate flaky tests.

Cover:

- Race conditions
- Poor locators
- Hard waits
- Network instability
- Environment instability
- Shared test data
- Test dependencies
- Async issues
- Incorrect assertions

Explain:

Bad approach → Why it fails → Enterprise solution.

---

PART 23 — DEBUGGING

Teach systematic debugging.

Cover:

- VS Code debugger
- Playwright Inspector
- Trace Viewer
- Screenshots
- Videos
- Console logs
- Network logs
- API logs
- Browser logs

Create a step-by-step failure investigation process.

---

PART 24 — PERFORMANCE AND SCALABILITY

Explain how to make Playwright automation scalable.

Cover:

- Parallel execution
- Workers
- Sharding
- Test isolation
- Resource optimization
- Browser reuse
- API-based setup
- Data management
- CI optimization

Explain how a framework can scale from:

100 tests → 1,000 tests → 10,000+ tests.

---

PART 25 — REAL ENTERPRISE PROJECT

Create a complete example project for a fictional enterprise application.

Use a realistic application such as:

"Enterprise Banking Web Application"

Include:

- Login
- MFA
- Dashboard
- Accounts
- Transactions
- Beneficiary
- Fund transfer
- Statements
- Logout

Build the framework progressively.

Show:

1. Requirements
2. Test strategy
3. Framework architecture
4. Folder structure
5. Installation
6. Configuration
7. POM
8. Fixtures
9. Test data
10. UI tests
11. API tests
12. Authentication
13. Utilities
14. Reports
15. Git
16. CI/CD
17. Azure DevOps
18. AI/Copilot assistance
19. MCP integration concepts
20. Maintenance strategy

---

PART 26 — REUSABLE TEMPLATE

Finally, create a generic automation framework template where domain-specific information can be replaced.

Use placeholders such as:

APPLICATION_NAME
APPLICATION_URL
DOMAIN
ENVIRONMENT
LOGIN_METHOD
AUTHENTICATION_TYPE
USER_ROLES
BUSINESS_MODULES
API_BASE_URL
TEST_DATA_SOURCE
CI_PLATFORM
REPOSITORY
REPORTING_TOOL

Create a reusable checklist:

Project Setup

[ ] Requirements
[ ] Test strategy
[ ] Tool selection
[ ] Repository
[ ] Playwright setup
[ ] TypeScript configuration
[ ] Environment configuration

Framework

[ ] POM
[ ] Components
[ ] Fixtures
[ ] Utilities
[ ] API layer
[ ] Test data
[ ] Authentication
[ ] Logging
[ ] Reporting

Testing

[ ] Smoke
[ ] Sanity
[ ] Regression
[ ] Functional
[ ] Negative
[ ] API
[ ] E2E
[ ] Cross-browser

CI/CD

[ ] Git
[ ] Pull request
[ ] Code review
[ ] Pipeline
[ ] Secrets
[ ] Parallel execution
[ ] Reports
[ ] Artifacts
[ ] Notifications

AI

[ ] Codegen
[ ] GitHub Copilot
[ ] MCP
[ ] AI-assisted test design
[ ] AI-assisted debugging
[ ] AI-assisted reporting

---

OUTPUT FORMAT

Structure the final knowledge base into:

1. Executive overview
2. Learning roadmap
3. Enterprise architecture
4. Detailed concepts
5. Installation/setup
6. Folder structure
7. Configuration
8. Coding examples
9. Complete framework example
10. AI/Copilot workflow
11. MCP workflow
12. Azure DevOps integration
13. CI/CD
14. Team management
15. Best practices
16. Anti-patterns
17. Troubleshooting
18. Interview questions
19. Enterprise checklist
20. Reusable project template

For code examples:

- Use TypeScript.
- Use modern Playwright Test practices.
- Provide complete runnable examples where practical.
- Explain important lines of code.
- Avoid outdated APIs and unnecessary complexity.
- Clearly distinguish demonstration code from production-ready code.

For architecture decisions, explain WHY the approach is recommended.

Do not simply provide a collection of disconnected tutorials. Build the knowledge as a coherent enterprise automation engineering system.

The final result should be something I can use as a master reference/skill blueprint and customize for almost any modern enterprise web application.
