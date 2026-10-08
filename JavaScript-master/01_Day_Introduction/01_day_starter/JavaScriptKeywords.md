JavaScript / TypeScript Keywords
# JavaScript / TypeScript Keywords

## 1. Control-flow keywords

These control the execution flow of your program.

| Keyword | Purpose |
| --- | --- |
| `if` | Execute code when a condition is true |
| `else` | Execute alternative code |
| `switch` | Choose between multiple cases |
| `case` | Define a switch option |
| `default` | Default switch option |
| `break` | Exit a loop or switch |
| `continue` | Skip the current loop iteration |
| `for` | Repeat code |
| `while` | Repeat while a condition is true |
| `do` | Execute the loop body at least once |
| `return` | Return a value from a function |

### `if`

Used to execute code when a condition is true.

```ts
const age = 25;

if (age >= 18) {
   console.log("Adult");
}
```

**Use in Playwright:**

```ts
if (await page.getByRole('button', { name: 'Login' }).isVisible()) {
   await page.getByRole('button', { name: 'Login' }).click();
}
```

### `else`

Runs when the `if` condition is false.

```ts
const age = 16;

if (age >= 18) {
   console.log("Adult");
} else {
   console.log("Minor");
}
```

### `switch`

Useful when you have multiple possible values.

```ts
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

```ts
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

Defines the fallback option.

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
break

Immediately exits a loop or switch.

for (let i = 1; i <= 10; i++) {
if (i === 5) {
break;
}

console.log(i);
}

Output:

1
2
3
4
Common use

Stopping a search when the required item is found.

continue

Skips the current iteration and moves to the next one.

for (let i = 1; i <= 5; i++) {
if (i === 3) {
continue;
}

console.log(i);
}

Output:

1
2
4
5
for

Used for repeated execution.

for (let i = 0; i < 5; i++) {
console.log(i);
}
Playwright example
for (let i = 0; i < 3; i++) {
await page.getByRole('button', { name: 'Submit' }).click();
}
while

Runs while a condition remains true.

let count = 1;

while (count <= 5) {
console.log(count);
count++;
}
do

Used with while; the code executes at least once.

let count = 1;

do {
console.log(count);
count++;
} while (count <= 5);

Difference:

while → condition checked first
do...while → code runs first, condition checked afterward
return

Returns a value from a function.

function add(a: number, b: number): number {
return a + b;
}

const result = add(10, 20);

console.log(result);

Output:

30
Playwright/POM
async getPageTitle(): Promise<string> {
return await this.page.title();
}
2. Error-handling keywords
Keyword	Purpose
try	Code that may produce an error
catch	Handle the error
finally	Execute code regardless of success/failure
throw	Manually generate an error
try
try {
const result = 10 / 0;
console.log(result);
} catch (error) {
console.log("Something went wrong");
}
catch

Handles an error.

try {
JSON.parse("invalid-json");
} catch (error) {
console.log("Invalid JSON");
}

In TypeScript:

try {
await page.goto("https://example.com");
} catch (error) {
console.error("Navigation failed:", error);
}
finally

Runs whether an error occurs or not.

try {
console.log("Executing");
} catch (error) {
console.log("Error");
} finally {
console.log("Cleanup");
}

Useful for cleanup activities.

throw

Creates your own error.

function validateAge(age: number) {
if (age < 18) {
throw new Error("Age must be 18 or above");
}
}
3. Variable declaration keywords
Keyword	Purpose
var	Old-style variable declaration
let	Block-scoped variable
const	Block-scoped constant/reference
var
var name = "Nikhil";

console.log(name);

var is older JavaScript syntax.

For modern TypeScript:

Prefer let and const.
Avoid var unless you specifically need legacy behavior.
let

Use when the value can change.

let count = 10;

count = 20;

console.log(count);
const

Use when the variable binding should not be reassigned.

const browser = "Chrome";

This is invalid:

const browser = "Chrome";

browser = "Firefox";
Important

const does not make an object immutable.

const user = {
name: "Nikhil"
};

user.name = "Rahul"; // Allowed

The reference cannot be reassigned, but object properties can change.

4. Functions and classes
   Keyword	Purpose
   function	Define a function
   class	Define a class
   new	Create an object
   this	Refer to current object/context
   extends	Inherit from another class
   super	Access parent class
   static	Define class-level member
   get	Getter
   set	Setter
   function
   function greet(name: string): string {
   return `Hello ${name}`;
   }

console.log(greet("Nikhil"));
class

Defines a class.

class Employee {
name: string;

constructor(name: string) {
this.name = name;
}
}
new

Creates an object.

const employee = new Employee("Nikhil");
Playwright
const loginPage = new LoginPage(page);

This is something you will use frequently with Page Object Model.

this

Refers to the current object.

class Employee {
name: string;

constructor(name: string) {
this.name = name;
}

printName() {
console.log(this.name);
}
}

In Playwright POM:

class LoginPage {
constructor(private page: Page) {}

async login() {
await this.page.getByRole('button', { name: 'Login' }).click();
}
}

Here:

this.page

means the page belonging to the current LoginPage object.

extends

Used for inheritance.

class Animal {
move() {
console.log("Moving");
}
}

class Dog extends Animal {
bark() {
console.log("Barking");
}
}

Now:

const dog = new Dog();

dog.move();
dog.bark();
Automation example

You might have:

class BasePage {
async waitForPage() {
// common functionality
}
}

class LoginPage extends BasePage {
async login() {
// login-specific functionality
}
}
super

Refers to the parent class.

class Animal {
constructor(public name: string) {}
}

class Dog extends Animal {
constructor(name: string) {
super(name);
}
}

super() calls the parent constructor.

static

Belongs to the class itself rather than an individual object.

class Config {
static baseUrl = "https://example.com";
}

console.log(Config.baseUrl);

You don't need:

new Config()

to access baseUrl.

get

Defines a getter.

class User {
constructor(private firstName: string) {}

get fullName() {
return this.firstName;
}
}

const user = new User("Nikhil");

console.log(user.fullName);
set

Defines a setter.

class User {
private name = "";

set userName(value: string) {
this.name = value;
}
}

const user = new User();

user.userName = "Nikhil";
5. Modules

These are extremely important in modern TypeScript projects.

Keyword	Purpose
export	Make code available to other files
import	Bring code from another file
as	Alias/import or type assertion
from	Specify source module
export
export class LoginPage {
}

Now another file can use it.

import
import { LoginPage } from './LoginPage';
Typical Playwright project
import { test, expect } from '@playwright/test';

This is something you will use constantly.

as

One use is aliasing:

import { LoginPage as Login } from './LoginPage';

Now:

const login = new Login(page);

Another TypeScript use is type assertion:

const value = someValue as string;
from

Specifies where something is imported from.

import { test } from '@playwright/test';

Here:

from '@playwright/test'

specifies the module source.

6. Operators and type-related keywords
   Keyword	Purpose
   in	Check property / iterate properties
   instanceof	Check object type/prototype relationship
   typeof	Determine JavaScript type
   delete	Remove object property
   void	Evaluate expression and return undefined
   in
   Property check
   const user = {
   name: "Nikhil",
   age: 28
   };

console.log("name" in user);

Output:

true
for...in
for (const key in user) {
console.log(key);
}
instanceof

Checks whether an object is an instance of a class.

class Car {}

const car = new Car();

console.log(car instanceof Car);

Output:

true
typeof

Returns the type of a value.

const name = "Nikhil";

console.log(typeof name);

Output:

string

Other examples:

typeof 10; // "number"
typeof true; // "boolean"
typeof undefined; // "undefined"
typeof {}; // "object"
delete

Removes a property from an object.

const user = {
name: "Nikhil",
age: 28
};

delete user.age;

console.log(user);

Result:

{ name: "Nikhil" }

Be careful: delete is generally not something you need frequently in automation code.

void

Evaluates an expression and returns undefined.

const result = void 0;

console.log(result);

Output:

undefined

A common use historically is:

void someFunction();

You won't normally need void in typical Playwright tests.

7. Exception/debugging keyword
   debugger

Pauses execution when a debugger is attached.

function calculate() {
const x = 10;
debugger;

return x * 2;
}

When debugging in VS Code/browser DevTools, execution can pause at that line.

Automation use
test('login test', async ({ page }) => {
await page.goto('https://example.com');

debugger;

await page.getByRole('button', { name: 'Login' }).click();
});

Useful when investigating automation problems.

8. Class and TypeScript-specific keywords

This area is especially important for your TypeScript learning.

Keyword	Purpose
interface	Define an object/type contract
implements	Class follows an interface
public	Accessible everywhere
private	Accessible only within class
protected	Accessible within class/subclasses
enum	Define named constants
package	Reserved/contextual word; rarely used in TS
static	Class-level member
interface

Defines the structure an object should have.

interface User {
name: string;
age: number;
}

const user: User = {
name: "Nikhil",
age: 28
};
Test data example
interface LoginData {
username: string;
password: string;
}

Then:

const data: LoginData = {
username: "testuser",
password: "password123"
};

This is very useful in automation frameworks.

implements

Requires a class to follow an interface.

interface Logger {
log(message: string): void;
}

class ConsoleLogger implements Logger {
log(message: string): void {
console.log(message);
}
}

The class must provide the required method.

public

Members are accessible from outside the class.

class User {
public name = "Nikhil";
}

const user = new User();

console.log(user.name);

In TypeScript, class members are public by default.

private

Only accessible inside the class.

class User {
private password = "12345";

login() {
console.log(this.password);
}
}

This is not allowed:

const user = new User();

console.log(user.password);
Playwright POM example
class LoginPage {
constructor(private page: Page) {}
}

Here page is private.

protected

Accessible within the class and subclasses.

class BasePage {
protected page: Page;

constructor(page: Page) {
this.page = page;
}
}

class LoginPage extends BasePage {
async login() {
await this.page.getByRole('button', { name: 'Login' }).click();
}
}
enum

Creates a set of named values.

enum Environment {
QA,
UAT,
PROD
}

console.log(Environment.QA);

String enums are often easier to read:

enum Environment {
QA = "qa",
UAT = "uat",
PROD = "prod"
}

Then:

const env = Environment.QA;
package

package is not normally used as a TypeScript programming keyword.

It is a reserved word in JavaScript grammar contexts and should not be treated as a normal variable name in strict/module code.

You normally won't use it in Playwright automation.

9. Async programming keywords

Extremely important for Playwright.

Keyword	Purpose
async	Defines an asynchronous function
await	Waits for a Promise
yield	Pauses a generator
async

Marks a function as asynchronous.

async function login() {
console.log("Login");
}

An async function returns a Promise.

await

Waits for a Promise to complete.

await page.goto('https://example.com');

This is fundamental to Playwright.

Another example:

const title = await page.title();

console.log(title);

Without understanding async/await, Playwright automation will be difficult to master.

yield

Used with generator functions.

function* numbers() {
yield 1;
yield 2;
yield 3;
}

const generator = numbers();

console.log(generator.next().value);

Output:

1

yield is not commonly required for normal Playwright automation, but you should understand what it means.

10. true, false, null, undefined

These are better described as literals/special values, not ordinary keywords.

true

Boolean true.

const isLoggedIn = true;
false

Boolean false.

const isAdmin = false;

Example:

if (isLoggedIn) {
console.log("User logged in");
}
null

Explicitly means "no value".

let selectedUser = null;

Think:

"There is intentionally no value."

undefined

Usually means a value has not been assigned.

let name;

console.log(name);

Output:

undefined

Important:

undefined is not technically a reserved keyword. It is a global identifier/property, although you should treat it as a special built-in value and not try to redefine it.

11. with

with adds an object's properties into the scope of a statement.

Example:

const user = {
name: "Nikhil",
age: 28
};

with (user) {
console.log(name);
}

However, with is forbidden in strict mode and is considered bad practice.

Modern TypeScript/Playwright code should not use it.

12. target

target is not a JavaScript/TypeScript keyword.

You may see it frequently in TypeScript configuration:

{
"compilerOptions": {
"target": "ES2022"
}
}

Here target is a tsconfig compiler option.

It tells TypeScript which JavaScript version to generate.

For example:

{
"compilerOptions": {
"target": "ES2022"
}
}

means:

Compile TypeScript into JavaScript compatible with ES2022.

So:

target ≠ JavaScript keyword
target = TypeScript compiler configuration option
Complete distribution of your list

Here is the easiest way to memorize everything.

A. Control Flow
if
else
switch
case
default
break
continue
for
while
do
return
B. Error Handling
try
catch
finally
throw
C. Variables
var
let
const
D. Functions
function
return
async
await
yield
E. Classes / OOP
class
new
this
extends
super
static
get
set
F. Modules
import
export
from
as
G. Operators / Type Checking
in
instanceof
typeof
delete
void
H. Debugging
debugger
I. TypeScript
interface
implements
public
private
protected
enum
J. Special values / literals
true
false
null
undefined
K. Legacy / restricted
with
package
L. Configuration, not a keyword
target
Most important for Playwright

You don't need to give equal importance to all of them.

For your Playwright + TypeScript SDET journey, prioritize them in this order:

Tier 1 — Must know
const
let
if
else
for
return
function
class
new
this
import
export
async
await
try
catch
throw
typeof
Tier 2 — Very important for frameworks
extends
super
interface
implements
public
private
protected
static
as
from
get
set
Tier 3 — Good to know
switch
case
default
break
continue
while
do
in
instanceof
delete
debugger
enum
yield
Tier 4 — Rarely needed in modern Playwright
with
package
void

And remember:

target → tsconfig option
undefined → special built-in value/identifier
true/false → boolean literals
null → null literal
A simple mental model

You can remember the keywords like this:

CONTROL
if / else / switch / case / for / while / break / continue

DATA
const / let / var

FUNCTION
function / return / async / await / yield

OBJECT / OOP
class / new / this / extends / super / static / get / set

ERROR
try / catch / finally / throw

MODULE
import / export / from / as

TYPE
typeof / instanceof / in

TYPESCRIPT
interface / implements / public / private / protected / enum

SPECIAL
true / false / null / undefined

DEBUG
debugger

For your automation career, const, let, function, class, this, new, import, export, async, await, interface, private, public, extends, try, catch, and return are the ones I would make completely automatic before moving deeper into Playwright framework architecture.
