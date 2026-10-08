# JavaScript / TypeScript Keywords

A comprehensive guide to keywords in JavaScript and TypeScript, tailored for SDETs and Playwright automation.

---

## 1. Control-Flow Keywords
These control the execution flow of your program.

| Keyword | Purpose |
| :--- | :--- |
| `if` | Execute code when condition is true |
| `else` | Execute alternative code |
| `switch` | Choose between multiple cases |
| `case` | Define a switch option |
| `default` | Default switch option |
| `break` | Exit loop/switch |
| `continue`| Skip current loop iteration |
| `for` | Repeat code |
| `while` | Repeat while condition is true |
| `do` | Execute loop body at least once |
| `return` | Return a value from a function |

### `if`
Used to execute code when a condition is true.
```typescript
const age = 25;
if (age >= 18) {
  console.log("Adult");
}
```
**Use in Playwright:**
```typescript
if (await page.getByRole('button', { name: 'Login' }).isVisible()) {
  await page.getByRole('button', { name: 'Login' }).click();
}
```

### `else`
Runs when the `if` condition is false.
```typescript
const age = 16;
if (age >= 18) {
  console.log("Adult");
} else {
  console.log("Minor");
}
```

### `switch`
Useful when you have multiple possible values.
```typescript
const browser = "chrome";
switch (browser) {
  case "chrome":
    console.log("Running Chrome");
    break;
  case "firefox":
    console.log("Running Firefox");
    break;
  default:
    console.log("Unknown browser");
}
```

### `case`
Defines an individual option inside `switch`.
```typescript
switch (status) {
  case "PASS":
    console.log("Test passed");
    break;
  case "FAIL":
    console.log("Test failed");
    break;
}
```

### `default`
Defines the fallback option in a `switch`.
```typescript
const role = "guest";
switch (role) {
  case "admin":
    console.log("Admin access");
    break;
  case "user":
    console.log("User access");
    break;
  default:
    console.log("Guest access");
}
```

### `break`
Immediately exits a loop or `switch`.
```typescript
for (let i = 1; i <= 10; i++) {
  if (i === 5) {
    break;
  }
  console.log(i);
}
// Output: 1, 2, 3, 4
```
*Common use:* Stopping a search when the required item is found.

### `continue`
Skips the current iteration and moves to the next one.
```typescript
for (let i = 1; i <= 5; i++) {
  if (i === 3) {
    continue;
  }
  console.log(i);
}
// Output: 1, 2, 4, 5
```

### `for`
Used for repeated execution.
```typescript
for (let i = 0; i < 5; i++) {
  console.log(i);
}
```
**Playwright example:**
```typescript
for (let i = 0; i < 3; i++) {
  await page.getByRole('button', { name: 'Submit' }).click();
}
```

### `while`
Runs while a condition remains true.
```typescript
let count = 1;
while (count <= 5) {
  console.log(count);
  count++;
}
```

### `do`
Used with `while`; the code executes at least once.
```typescript
let count = 1;
do {
  console.log(count);
  count++;
} while (count <= 5);
```
*Difference:* `while` checks the condition first. `do...while` runs the code first, then checks the condition.

### `return`
Returns a value from a function.
```typescript
function add(a: number, b: number): number {
  return a + b;
}
const result = add(10, 20);
console.log(result); // Output: 30
```
**Playwright/POM example:**
```typescript
async getPageTitle(): Promise<string> {
  return await this.page.title();
}
```

---

## 2. Error-Handling Keywords

| Keyword | Purpose |
| :--- | :--- |
| `try` | Code that may produce an error |
| `catch` | Handle the error |
| `finally` | Execute code regardless of success/failure |
| `throw` | Manually generate an error |

### `try` & `catch`
`try` attempts to execute code, and `catch` handles the error if one occurs.
```typescript
try {
  const result = 10 / 0;
  console.log(result);
} catch (error) {
  console.log("Something went wrong");
}

try {
  JSON.parse("invalid-json");
} catch (error) {
  console.log("Invalid JSON");
}
```
**In Playwright/TypeScript:**
```typescript
try {
  await page.goto("https://example.com");
} catch (error) {
  console.error("Navigation failed:", error);
}
```

### `finally`
Runs whether an error occurs or not. Useful for cleanup activities.
```typescript
try {
  console.log("Executing");
} catch (error) {
  console.log("Error");
} finally {
  console.log("Cleanup");
}
```

### `throw`
Creates your own error.
```typescript
function validateAge(age: number) {
  if (age < 18) {
    throw new Error("Age must be 18 or above");
  }
}
```

---

## 3. Variable Declaration Keywords

| Keyword | Purpose |
| :--- | :--- |
| `var` | Old-style variable declaration |
| `let` | Block-scoped variable |
| `const` | Block-scoped constant/reference |

### `var`
```typescript
var name = "Nikhil";
console.log(name);
```
*Note:* `var` is older JavaScript syntax. For modern TypeScript, prefer `let` and `const`. Avoid `var` unless you specifically need legacy behavior.

### `let`
Use when the value can change.
```typescript
let count = 10;
count = 20;
console.log(count);
```

### `const`
Use when the variable binding should not be reassigned.
```typescript
const browser = "Chrome";
// browser = "Firefox"; // This is invalid
```
*Important:* `const` does not make an object immutable. The reference cannot be reassigned, but object properties can change.
```typescript
const user = { name: "Nikhil" };
user.name = "Rahul"; // Allowed
```

---

## 4. Functions and Classes

| Keyword | Purpose |
| :--- | :--- |
| `function`| Define a function |
| `class` | Define a class |
| `new` | Create an object |
| `this` | Refer to current object/context |
| `extends` | Inherit from another class |
| `super` | Access parent class |
| `static` | Define class-level member |
| `get` | Getter |
| `set` | Setter |

### `function`
```typescript
function greet(name: string): string {
  return `Hello ${name}`;
}
console.log(greet("Nikhil"));
```

### `class` & `new`
`class` defines a blueprint, and `new` creates an instance (object) of that class.
```typescript
class Employee {
  name: string;
  constructor(name: string) {
    this.name = name;
  }
}

const employee = new Employee("Nikhil");
```
**Playwright POM:**
```typescript
const loginPage = new LoginPage(page);
```

### `this`
Refers to the current object.
```typescript
class Employee {
  name: string;

  constructor(name: string) {
    this.name = name;
  }

  printName() {
    console.log(this.name);
  }
}
```
**In Playwright POM:**
```typescript
class LoginPage {
  constructor(private page: Page) {}

  async login() {
    await this.page.getByRole('button', { name: 'Login' }).click();
  }
}
```
Here, `this.page` means the page belonging to the current `LoginPage` object.

### `extends` & `super`
`extends` is used for inheritance. `super` calls the parent class constructor.
```typescript
class Animal {
  constructor(public name: string) {}
  move() {
    console.log("Moving");
  }
}

class Dog extends Animal {
  constructor(name: string) {
    super(name);
  }
  bark() {
    console.log("Barking");
  }
}

const dog = new Dog("Buddy");
dog.move();
dog.bark();
```
**Automation Example:**
```typescript
class BasePage {
  async waitForPage() { /* common functionality */ }
}

class LoginPage extends BasePage {
  async login() { /* login-specific functionality */ }
}
```

### `static`
Belongs to the class itself rather than an individual object. You don't need `new` to access it.
```typescript
class Config {
  static baseUrl = "https://example.com";
}
console.log(Config.baseUrl); // Access directly on the class
```

### `get` & `set`
Define getters and setters for properties.
```typescript
class User {
  private _name = "";

  constructor(private firstName: string) {}

  get fullName() {
    return this.firstName;
  }
  
  set userName(value: string) {
    this._name = value;
  }
}

const user = new User("Nikhil");
console.log(user.fullName); // Calls getter
user.userName = "Nikhil"; // Calls setter
```

---

## 5. Modules

These are extremely important in modern TypeScript projects.

| Keyword | Purpose |
| :--- | :--- |
| `export` | Make code available to other files |
| `import` | Bring code from another file |
| `as` | Alias/import or type assertion |
| `from` | Specify source module |

### `export`
```typescript
export class LoginPage {
}
```
Now another file can use it.

### `import` & `from`
```typescript
import { LoginPage } from './LoginPage';
```
**Typical Playwright project:**
```typescript
import { test, expect } from '@playwright/test';
```

### `as`
Used for aliasing imports or type assertions.
```typescript
// Aliasing
import { LoginPage as Login } from './LoginPage';
const login = new Login(page);

// Type Assertion
const value = someValue as string;
```

---

## 6. Operators and Type-Related Keywords

| Keyword | Purpose |
| :--- | :--- |
| `in` | Check property / iterate properties |
| `instanceof`| Check object type/prototype relationship |
| `typeof` | Determine JavaScript type |
| `delete` | Remove object property |
| `void` | Evaluate expression and return undefined |

### `in`
```typescript
const user = { name: "Nikhil", age: 28 };
console.log("name" in user); // Output: true

for (const key in user) {
  console.log(key);
}
```

### `instanceof`
```typescript
class Car {}
const car = new Car();
console.log(car instanceof Car); // Output: true
```

### `typeof`
```typescript
console.log(typeof "Nikhil"); // "string"
console.log(typeof 10);        // "number"
console.log(typeof true);      // "boolean"
console.log(typeof undefined); // "undefined"
console.log(typeof {});        // "object"
```

### `delete`
```typescript
const user: any = { name: "Nikhil", age: 28 };
delete user.age;
console.log(user); // Result: { name: "Nikhil" }
```
*Note:* `delete` is generally not something you need frequently in automation code.

### `void`
Evaluates an expression and returns undefined.
```typescript
const result = void 0;
console.log(result); // Output: undefined
```

---

## 7. Exception/Debugging Keyword

### `debugger`
Pauses execution when a debugger is attached.
```typescript
function calculate() {
  const x = 10;
  debugger;
  return x * 2;
}
```
**Automation use:**
```typescript
test('login test', async ({ page }) => {
  await page.goto('https://example.com');
  debugger; // Pauses execution here if DevTools are open
  await page.getByRole('button', { name: 'Login' }).click();
});
```

---

## 8. Class and TypeScript-Specific Keywords

| Keyword | Purpose |
| :--- | :--- |
| `interface` | Define an object/type contract |
| `implements`| Class follows an interface |
| `public` | Accessible everywhere |
| `private` | Accessible only within class |
| `protected` | Accessible within class/subclasses |
| `enum` | Define named constants |
| `package` | Reserved/contextual word (rarely used) |

### `interface` & `implements`
```typescript
interface Logger {
  log(message: string): void;
}

class ConsoleLogger implements Logger {
  log(message: string): void {
    console.log(message);
  }
}
```
**Test Data Example:**
```typescript
interface LoginData {
  username: string;
  password: string;
}
const data: LoginData = {
  username: "testuser",
  password: "password123"
};
```

### `public`, `private`, `protected`
*   **`public`**: Accessible everywhere (default).
*   **`private`**: Accessible only within the class.
*   **`protected`**: Accessible within the class and subclasses.

```typescript
class BasePage {
  protected page: Page;
  constructor(page: Page) { this.page = page; }
}

class LoginPage extends BasePage {
  private password = "123";
  public async login() {
     // Can access protected this.page and private this.password
  }
}
```

### `enum`
```typescript
enum Environment {
  QA = "qa",
  UAT = "uat",
  PROD = "prod"
}
const env = Environment.QA;
```

---

## 9. Async Programming Keywords

| Keyword | Purpose |
| :--- | :--- |
| `async` | Defines an asynchronous function |
| `await` | Waits for a Promise |
| `yield` | Pauses a generator |

### `async` & `await`
Fundamental for Playwright. `async` functions return a Promise, and `await` pauses execution until the Promise resolves.
```typescript
async function login() {
  await page.goto('https://example.com');
  const title = await page.title();
  console.log(title);
}
```

### `yield`
Used with generator functions.
```typescript
function* numbers() {
  yield 1;
  yield 2;
}
const generator = numbers();
console.log(generator.next().value); // Output: 1
```

---

## 10. Special Values / Literals

*   **`true` / `false`**: Boolean literals.
*   **`null`**: Explicitly means "no value".
*   **`undefined`**: Usually means a value has not been assigned. (Technically a global identifier, but treated as a special value).

---

## 11. Legacy / Restricted

### `with`
Adds an object's properties into the scope. *Forbidden in strict mode and considered bad practice. Do not use in modern TypeScript.*

---

## 12. Not a Keyword: `target`

`target` is a `tsconfig.json` compiler option, telling TypeScript which JavaScript version to generate (e.g., `"target": "ES2022"`), not a programming keyword.

---

## Summary & Priorities for Playwright

### Tier 1 — Must know
`const`, `let`, `if`, `else`, `for`, `return`, `function`, `class`, `new`, `this`, `import`, `export`, `async`, `await`, `try`, `catch`, `throw`, `typeof`

### Tier 2 — Very important for frameworks
`extends`, `super`, `interface`, `implements`, `public`, `private`, `protected`, `static`, `as`, `from`, `get`, `set`

### Tier 3 — Good to know
`switch`, `case`, `default`, `break`, `continue`, `while`, `do`, `in`, `instanceof`, `delete`, `debugger`, `enum`, `yield`

### Tier 4 — Rarely needed in modern Playwright
`with`, `package`, `void`