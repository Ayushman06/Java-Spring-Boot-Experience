# ☕ Java Spring Boot Developer Roadmap
### A Beginner-to-Intermediate Survival Guide

> *"An investment in knowledge pays the best interest."* — Benjamin Franklin

This roadmap is your structured companion for becoming a confident Java Spring Boot developer. Whether you're just starting out or solidifying your intermediate knowledge, follow this guide top to bottom. Each section builds on the last. Don't rush — depth beats breadth.

---

## 📋 Table of Contents

1. [Java Foundations](#1-java-foundations)
2. [Java Version Milestones](#2-java-version-milestones)
3. [Data Structures and Algorithms](#3-data-structures-and-algorithms)
4. [Spring & Spring Boot Overview](#4-spring--spring-boot-overview)
5. [Microservices & Communication](#5-microservices--communication)
6. [Deployment & Tools](#6-deployment--tools)
7. [Incorporating AI into Spring Boot](#7-incorporating-ai-into-spring-boot)
8. [Additional Resources](#8-additional-resources)
9. [Common Mistakes to Avoid](#9-common-mistakes-to-avoid)
10. [Suggested Learning Path](#10-suggested-learning-path)

---

## 1. Java Foundations

> Master these before you touch Spring Boot. A shaky foundation breaks any building. The single biggest mistake beginners make is jumping straight to Spring without knowing Java well enough — you'll get stuck on problems that have nothing to do with Spring.

### 🔷 Core Language & OOP

- **OOP Principles** — The four pillars: Encapsulation, Inheritance, Polymorphism, Abstraction; the entire Spring framework is built on these.
- **SOLID Principles** — Five design guidelines (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) that make codebases maintainable and extensible; Spring's DI pattern is a direct application of the Dependency Inversion Principle.
- **Classes & Objects** — Blueprints and instances; everything in Java is an object (except primitives).
- **Interfaces & Abstract Classes** — Interfaces define *what* a class must do; abstract classes define a partial *how*; choose based on whether you need shared state.
- **static & final keywords** — `static` belongs to the class, not the instance; `final` prevents reassignment, overriding, or subclassing depending on where it's applied.
- **Access Modifiers** — `public`, `private`, `protected`, package-private; they enforce encapsulation and control the boundaries of your API.
- **Generics** — Write type-safe code once and reuse for any type (`List<T>`); prevents the runtime `ClassCastException` that plagued pre-Java 5 code.
- **Enums** — Named constants with type safety and the ability to carry fields and methods; far better than magic strings or integer flags.
- **Wrapper Classes & Autoboxing** — Automatic conversion between primitives (`int`) and their object equivalents (`Integer`); be careful with `==` comparison on wrapper types.

---

### 🔷 Collections Framework

The backbone of everyday Java programming. You will use these in virtually every function you write — know them cold.

- **List** (`ArrayList`, `LinkedList`) — Ordered, allows duplicates; `ArrayList` is O(1) for reads, `LinkedList` is O(1) for inserts/deletes at either end.
- **Set** (`HashSet`, `LinkedHashSet`, `TreeSet`) — No duplicates; `HashSet` is O(1) average, `TreeSet` is O(log n) but keeps elements sorted.
- **Map** (`HashMap`, `LinkedHashMap`, `TreeMap`) — Key-value pairs; `HashMap` is the most common (O(1) average), `TreeMap` maintains sorted key order.
- **Queue & Deque** (`PriorityQueue`, `ArrayDeque`) — FIFO and double-ended structures; `ArrayDeque` beats `Stack` for both stack and queue use.
- **Collections utility class** — Static helpers: `sort()`, `reverse()`, `shuffle()`, `frequency()`, `unmodifiableList()`; saves you from reinventing common operations.
- **Comparable vs Comparator** — `Comparable` is natural ordering baked into the class itself; `Comparator` is external ordering you define at the call site.
- **Fail-fast vs Fail-safe Iterators** — Fail-fast throws `ConcurrentModificationException` if you modify a collection while iterating; fail-safe (`CopyOnWriteArrayList`) iterates over a snapshot copy.
- **HashMap internals** — Interviewers love this: HashMap uses an array of linked lists (buckets), hashing the key to find the bucket, and `equals()` to find the exact entry; in Java 8+, buckets convert to balanced trees when they exceed 8 entries.

---

### 🔷 Multithreading & Concurrency

Modern applications are concurrent by default — Spring Boot handles thousands of requests simultaneously. If you don't understand this section, you will write impossible-to-reproduce bugs.

- **Thread & Runnable** — The two original ways to create threads; prefer `Runnable` (and `Callable`) since Java doesn't support multiple class inheritance.
- **Callable & Future** — Like `Runnable` but returns a result and can throw checked exceptions; `Future.get()` blocks until the result is ready.
- **ExecutorService & Thread Pool** — Reuse threads from a managed pool instead of creating new ones; `Executors.newFixedThreadPool(n)` is the starting point; creating raw threads in a loop is an antipattern.
- **synchronized keyword** — Prevents multiple threads from executing a block simultaneously; your first line of defense against race conditions but comes with performance cost.
- **volatile keyword** — Ensures a variable's value is always read from main memory, not a thread's local cache; fixes visibility issues but doesn't fix atomicity issues.
- **ReentrantLock** — More flexible than `synchronized`; supports try-lock (non-blocking attempt), timed lock, and interruptible locking.
- **Atomic Classes** (`AtomicInteger`, `AtomicLong`, `AtomicReference`) — Thread-safe CAS (compare-and-swap) operations on single variables without the overhead of full synchronization.
- **CompletableFuture** — Modern, composable async programming; chain operations with `.thenApply()`, `.thenCombine()`, `.thenCompose()`, `.exceptionally()`; the right tool for non-blocking service calls.
- **Deadlock, Livelock, Starvation** — Three concurrency nightmares; prevent deadlock with consistent lock ordering and timeouts, starvation with fair locks.
- **ThreadLocal** — Gives each thread its own isolated copy of a variable; Spring uses this internally for request-scoped context (SecurityContextHolder, TransactionSynchronizationManager).

---

### 🔷 Exception Handling

90% of production code is correct error handling, not happy-path logic.

- **Checked vs Unchecked Exceptions** — Checked (`IOException`, `SQLException`) must be handled at compile time; unchecked (`RuntimeException`, `NullPointerException`) are discovered at runtime. In Spring Boot, prefer unchecked custom exceptions.
- **try-catch-finally** — `finally` always runs regardless of exception; use it for guaranteed cleanup (closing connections, releasing locks).
- **try-with-resources** — Automatically closes any `AutoCloseable` resource; cleaner, safer, and less code than manual `finally` blocks.
- **Custom Exceptions** — Extend `RuntimeException` to create domain-specific exceptions (`BookingNotFoundException`, `InsufficientInventoryException`) that carry meaningful context.
- **Exception chaining** — Wrap low-level exceptions in higher-level ones using the `cause` constructor parameter; preserves the original stack trace for debugging.
- **Global Exception Handling** — In Spring Boot, `@ControllerAdvice` + `@ExceptionHandler` centralizes all error responses in one place; every controller benefit automatically.

---

### 🔷 Java 8+ Functional Features

Spring Boot heavily leverages these. Understand them, not just the syntax.

- **Lambda Expressions** — Anonymous functions without the ceremony; `(a, b) -> a + b` replaces an entire anonymous class declaration.
- **Stream API** — Declarative pipeline for processing collections: `filter()` → `map()` → `sorted()` → `collect()`; does not mutate the source.
- **Optional** — A container that explicitly models the possibility of absence; use `orElse()`, `orElseThrow()`, `ifPresent()` — don't use `Optional.get()` blindly, it defeats the purpose.
- **Functional Interfaces** (`Function<T,R>`, `Predicate<T>`, `Consumer<T>`, `Supplier<T>`) — Single abstract method interfaces; used as lambda targets throughout the JDK and Spring.
- **Method References** — Shorthand for simple lambdas: `String::toUpperCase`, `this::processOrder`, `Order::new`.
- **Default & Static Methods in Interfaces** — Interfaces can carry concrete implementations; enables backward-compatible API evolution without breaking implementors.

---

### 🔷 JDBC & Database Basics

Don't skip this to jump straight to JPA. Understanding what JPA is generating underneath makes you far better at debugging and performance tuning.

- **JDBC (Java Database Connectivity)** — The standard Java API for connecting to any relational database and executing SQL directly; JPA is built on top of it.
- **Connection** — Represents a physical database connection; always close it (use try-with-resources); connections are expensive to create, which is why connection pools exist.
- **Connection Pooling** (`HikariCP`) — Spring Boot's default pool; instead of creating a new connection per request, reuses connections from a managed pool — critical for performance at any scale.
- **PreparedStatement** — Precompiled SQL with parameter placeholders; prevents SQL injection and is more efficient for repeated queries.
- **Transactions** — A group of operations that either all succeed (COMMIT) or all fail (ROLLBACK); the foundation of data integrity. Understand `ACID` properties.
- **Isolation Levels** — Controls how concurrent transactions see each other's changes; from weakest to strongest: `READ_UNCOMMITTED` → `READ_COMMITTED` → `REPEATABLE_READ` → `SERIALIZABLE`.

---

### 🔷 I/O & Serialization

- **File I/O (NIO.2)** — The `Files`, `Path`, and `Paths` classes introduced in Java 7; cleaner and more powerful than the old `java.io.File`.
- **Serialization** — Converting objects to byte streams for storage or network transfer; implement `Serializable` and always declare `serialVersionUID` to avoid versioning surprises.
- **Jackson / Gson** — Libraries for JSON serialization/deserialization; Jackson is Spring Boot's default (`ObjectMapper`); understand `@JsonProperty`, `@JsonIgnore`, `@JsonInclude`.

---

### 🔷 Testing Basics

- **JUnit 5** — The standard Java testing framework; `@Test`, `@BeforeEach`, `@AfterEach`, `@ParameterizedTest`; run from Maven with `mvn test`.
- **Mockito** — Mock dependencies so you test a class in isolation; `@Mock` creates a mock, `@InjectMocks` injects them, `when().thenReturn()` defines behavior.
- **Spring Boot Test** — `@SpringBootTest` loads the full application context for integration tests; `@WebMvcTest` loads only the web layer; `@DataJpaTest` loads only the JPA layer.
- **Test Pyramid** — Write many unit tests (fast, isolated), fewer integration tests (slower, tests wiring), and minimal end-to-end tests.

---

### 🔷 JVM Internals (Know the Basics)

- **JVM Memory Model** — Heap (all objects), Stack (method frames and local variables, one per thread), Method Area / Metaspace (class metadata), and native memory.
- **Garbage Collection** — Automatic memory management; the JVM reclaims objects that are no longer reachable. Know G1GC basics (Spring Boot's default), and the key flags: `-Xmx` (max heap), `-Xms` (initial heap).
- **Class Loading** — How the JVM loads, links, and initializes classes at runtime using a delegation hierarchy (Bootstrap → Extension → Application ClassLoader).

---

## 2. Java Version Milestones

> Each LTS release changed how production Java is written. For interviews, know what came when and why it mattered. Skip non-LTS versions in production — they're almost never used in industry.

### Java 8 (LTS) — *The Modern Java Revolution*
Released 2014. Still running in ~40% of existing enterprise applications.

- **Lambda Expressions** — Brought functional programming style to Java; the single biggest change to the language in a decade.
- **Stream API** — Declarative, pipeline-based collection processing; replaced most `for` loops in modern code.
- **Optional** — A proper, explicit way to model nullable return values.
- **Default Methods in Interfaces** — Interfaces can now carry implementation; enabled the JDK's own `Collection` API to evolve without breaking existing code.
- **java.time API** (`LocalDate`, `LocalDateTime`, `ZonedDateTime`) — Replaced the notoriously broken `Date` and `Calendar` classes.
- **CompletableFuture** — Composable asynchronous programming; replaced manual `Future` chaining.

### Java 9 — *Modular Java*
- **JPMS (Java Platform Module System)** — Enforces encapsulation at the JAR/package level via `module-info.java`; you only expose what you explicitly export.
- **JShell** — An interactive REPL for experimenting with Java without writing a full class.
- **Collection Factory Methods** — `List.of()`, `Set.of()`, `Map.of()` for concise immutable collections; less verbose than `Arrays.asList()`.

### Java 10 — *`var` Arrives*
- **`var` (Local Variable Type Inference)** — The compiler infers the type from the right-hand side; `var list = new ArrayList<String>()` is valid. Reduces verbosity without sacrificing type safety.

### Java 11 (LTS) — *String and HTTP Upgrades*
Released 2018. The default for conservative teams.

- **New String Methods** — `isBlank()`, `strip()` (Unicode-aware), `lines()`, `repeat(n)` make string manipulation cleaner.
- **`var` in Lambda Parameters** — Allows annotations on inferred lambda parameters: `(@NotNull var s) -> s.length()`.
- **HttpClient (Standard)** — A modern, asynchronous HTTP client built into the JDK; finally replaces the ancient `HttpURLConnection`.
- **Single-File Source Programs** — Run a `.java` file directly with `java Hello.java` without a separate compile step; great for scripts.

### Java 17 (LTS) — *Sealed and Structured*
Released 2021. Required by Spring Boot 3.x. The current standard — 90% of new projects start here.

- **Sealed Classes** — Restricts which classes can extend or implement a type; `sealed interface Shape permits Circle, Rectangle`; perfect for closed domain hierarchies.
- **Records** — Compact, immutable data carriers with auto-generated constructor, `equals()`, `hashCode()`, `toString()`; replaces verbose data/value classes.
- **Pattern Matching for `instanceof`** — Eliminates the redundant cast: `if (obj instanceof String s) { s.length(); }`.
- **Text Blocks** — Multiline strings without escape sequences; perfect for inline JSON, SQL, and HTML in tests.
- **Stronger JDK Encapsulation** — Internal JDK APIs (`sun.*`, `com.sun.*`) are no longer accessible by default; forces use of proper public APIs.

### Java 21 (LTS) — *The Concurrency Revolution*
Released 2023. Fully supported by Spring Boot 3.2+.

- **Virtual Threads (Project Loom)** — Lightweight threads managed entirely by the JVM, not the OS; you can have millions of them without the overhead of platform threads. Fundamentally changes IO-bound throughput for Spring Boot applications.
- **Sequenced Collections** — New interfaces (`SequencedCollection`, `SequencedMap`) that provide a standard way to access the first and last elements of any ordered collection.
- **Pattern Matching for `switch`** — `switch` can now match on types, deconstruct patterns, and handle `null` explicitly.
- **Record Patterns** — Deconstruct record components directly in pattern matching: `if (shape instanceof Circle(double r)) { ... }`.

> ✅ Recommendation: Learn on Java 17. It's the current production standard and is required by Spring Boot 3.x. Virtual Threads in Java 21 are worth understanding conceptually even if your current project runs on 17.

---

## 3. Data Structures and Algorithms

> DSA is how you think, not just what you memorize. It sharpens every problem-solving muscle. For Spring Boot interviews, depth on Collections, HashMap internals, and concurrency matters more than competitive programming tricks. But don't ignore it entirely — coding rounds are real.

### 🔷 Core Data Structures

| Structure | Best For | Java Implementation |
|---|---|---|
| Array | Fixed-size, O(1) indexed access | `int[]`, `String[]` |
| Dynamic Array | Growable indexed list | `ArrayList` |
| Linked List | Frequent inserts/deletes at edges | `LinkedList` |
| Stack | LIFO — undo, backtracking, DFS | `ArrayDeque` (as stack) |
| Queue | FIFO — task scheduling, BFS | `ArrayDeque`, `LinkedList` |
| Priority Queue | Always get the min/max element | `PriorityQueue` |
| HashMap | Fast O(1) average key-value lookup | `HashMap` |
| TreeMap | Sorted key-value access | `TreeMap` |
| HashSet | Fast membership check, no duplicates | `HashSet` |
| Graph | Relationships, paths, networks | Custom adjacency list/matrix |
| Tree / BST | Hierarchical, sorted structure | Custom or `TreeMap` |
| Trie | Prefix search, autocomplete | Custom implementation |
| Heap | Efficient min/max tracking | `PriorityQueue` |

---

### 🔷 Core Algorithms

- **Binary Search** — O(log n) search in sorted arrays; master the `left <= right` loop pattern and understand when `mid = left + (right - left) / 2` matters (overflow prevention).
- **Sorting** — Merge Sort (O(n log n), stable, good for linked lists), Quick Sort (O(n log n) average, fast in practice), Heap Sort (O(n log n) worst case); `Arrays.sort()` uses TimSort (hybrid Merge + Insertion).
- **BFS (Breadth-First Search)** — Level-by-level traversal using a queue; finds the shortest path in an unweighted graph.
- **DFS (Depth-First Search)** — Deep traversal using recursion or a stack; cycle detection, topological sort, connected components.
- **Dynamic Programming (DP)** — Break into overlapping subproblems and cache results; progression: naive recursion → memoization (top-down) → tabulation (bottom-up).
- **Sliding Window** — O(n) technique for contiguous subarray/substring problems; avoids redundant recomputation by maintaining a window as it moves.
- **Two Pointers** — O(n) technique for sorted arrays; two indices that move inward or outward replace an O(n²) nested loop.
- **Backtracking** — Explore all possibilities and prune bad branches early; used in permutations, combinations, N-Queens, Sudoku.
- **Greedy Algorithms** — Make the locally optimal choice at each step; works when local optima guarantee global optima (e.g., interval scheduling, Huffman coding).

---

### 🔷 Complexity Analysis

- **Time Complexity** — How runtime grows relative to input size; express in Big-O notation: O(1) constant, O(log n) logarithmic, O(n) linear, O(n log n) linearithmic, O(n²) quadratic.
- **Space Complexity** — How memory usage grows; don't forget the call stack in recursive algorithms.
- **Amortized Analysis** — Average cost per operation over a sequence; `ArrayList.add()` is O(n) when resizing but O(1) amortized because resizing is rare.

---

### 🔷 Practice Strategy

- Start with LeetCode Easy → Medium; focus on patterns, not memorizing solutions.
- Work through these patterns in order: Arrays & Hashing → Two Pointers → Sliding Window → Binary Search → BFS/DFS → DP.
- Use NeetCode.io's structured roadmap — it groups problems by pattern and comes with video explanations.
- Aim for 2–3 problems per week consistently rather than grinding 20 problems in one weekend.

---

## 4. Spring & Spring Boot Overview

> Spring is the engine. Spring Boot is that engine pre-configured and ready to drive. They are not competitors — Spring Boot is built on top of Spring Framework.

### 🔷 Spring Framework Core

- **Dependency Injection (DI)** — The framework creates and "injects" your dependencies instead of you manually wiring them with `new`; the single most important concept in all of Spring.
- **Inversion of Control (IoC)** — You surrender control of object creation to the container; the container manages lifecycle (creation, configuration, destruction).
- **IoC Container / ApplicationContext** — The central container that holds all beans and resolves their dependencies at startup; `ApplicationContext` is the full-featured version of `BeanFactory`.
- **Bean** — Any object managed by the Spring IoC container; declared via `@Component`, `@Service`, `@Repository`, `@Controller`, or `@Bean` methods.
- **Bean Scopes** — `singleton` (one instance per container, default), `prototype` (new instance per injection point), `request`/`session` (web-specific, one per HTTP request/session).
- **`@Autowired`** — Instructs Spring to inject a dependency automatically; prefer constructor injection over field injection — it makes dependencies explicit and the class testable without Spring.
- **`@Qualifier`** — Disambiguates when multiple beans of the same type exist in the context; use alongside `@Autowired`.
- **`@Primary`** — Marks one bean as the default when multiple candidates exist; `@Qualifier` overrides it.
- **`@Configuration` & `@Bean`** — Java-based configuration; `@Configuration` is a class that defines beans; `@Bean` methods produce managed instances.
- **`@ComponentScan`** — Tells Spring which packages to scan for annotated classes; `@SpringBootApplication` does this automatically for the package it lives in and all sub-packages.
- **AOP (Aspect-Oriented Programming)** — Separates cross-cutting concerns (logging, security, transaction, metrics) from business logic using `@Aspect`, `@Before`, `@After`, `@Around`; Spring's `@Transactional` is implemented as an AOP proxy.
- **Spring Events** — Internal publish/subscribe using `ApplicationEventPublisher` and `@EventListener`; decouples components without introducing a full message broker.

---

### 🔷 Spring Boot Specifics

- **Auto-Configuration** — Spring Boot reads the classpath and conditionally configures beans automatically (`@ConditionalOnClass`, `@ConditionalOnMissingBean`); adding `spring-boot-starter-data-jpa` to your POM is enough to get a fully configured JPA context.
- **`@SpringBootApplication`** — Combines `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan`; one annotation to bootstrap everything.
- **Embedded Server** — Tomcat (default), Jetty, or Undertow is bundled inside the JAR; run with `java -jar app.jar`, no external server deployment needed.
- **Starters** — Curated dependency bundles; `spring-boot-starter-web` brings Tomcat + Spring MVC + Jackson in one line of POM.
- **`application.properties` / `application.yml`** — Centralized configuration for all Spring Boot settings; environment-specific overrides via Spring Profiles.
- **Profiles** (`@Profile`, `spring.profiles.active=dev`) — Activate different sets of beans and configuration for `dev`, `test`, and `prod` without touching code.
- **Actuator** — Exposes production-ready management endpoints: `/actuator/health`, `/actuator/metrics`, `/actuator/info`, `/actuator/env`; Kubernetes uses `/health` for liveness/readiness probes.
- **Spring Boot DevTools** — Auto-restarts the app on classpath changes during development; dramatically improves the edit-run-test cycle.
- **Configuration Properties** (`@ConfigurationProperties`) — Bind a group of related properties from `application.yml` into a type-safe Java class; cleaner than scattering `@Value` annotations everywhere.

---

### 🔷 Spring MVC — The Web Layer

- **`@RestController`** — Combines `@Controller` and `@ResponseBody`; every method's return value is serialized to JSON/XML directly in the HTTP response body.
- **`@RequestMapping` / `@GetMapping` / `@PostMapping` / `@PutMapping` / `@DeleteMapping`** — Map HTTP verbs and URL patterns to handler methods.
- **`@PathVariable`** — Binds a URI template segment (`/users/{id}`) to a method parameter.
- **`@RequestParam`** — Binds a query string parameter (`/users?page=1&size=20`) to a method parameter.
- **`@RequestBody`** — Deserializes the HTTP request body JSON into a Java object using Jackson.
- **`ResponseEntity<T>`** — Gives full control over HTTP status code, response headers, and body; prefer this over raw return values when status or headers matter.
- **DTO (Data Transfer Object)** — A plain class used to carry data between layers and over the wire; never expose your `@Entity` objects directly in API responses — it leaks database structure and creates tight coupling.
- **`@Valid` / `@Validated`** — Triggers Bean Validation on `@RequestBody` and `@PathVariable`; works with JSR-380 annotations like `@NotNull`, `@NotBlank`, `@Size`, `@Min`, `@Email`.
- **`@ControllerAdvice` + `@ExceptionHandler`** — Global exception handler across all controllers; maps exceptions to consistent JSON error responses.
- **`HandlerInterceptor`** — Intercepts requests before/after controller execution; useful for auth checks, request logging, and elapsed time measurement.
- **Filter vs Interceptor** — Filters are servlet-level (run before Spring sees the request); Interceptors are Spring MVC-level (run after servlet dispatch, before the controller).

---

### 🔷 Data Access — JPA, Hibernate & Spring Data

- **JPA (Java Persistence API)** — A specification (interface contract) for ORM; maps Java objects to database tables via annotations.
- **Hibernate** — The most popular JPA implementation; Spring Boot uses it by default and auto-configures it from `application.properties`.
- **`@Entity` & `@Table`** — Mark a class as a JPA-managed entity; `@Table(name="...")` maps it to a specific table name.
- **`@Id` & `@GeneratedValue`** — Define the primary key and its generation strategy: `IDENTITY` (auto-increment column), `SEQUENCE` (DB sequence), `UUID`.
- **Relationships** — `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`; understand `mappedBy` (who owns the FK), `CascadeType` (do operations propagate?), and `FetchType`.
- **FetchType LAZY vs EAGER** — `LAZY` loads related data on demand (strongly prefer this); `EAGER` loads immediately and is the root cause of N+1 problems.
- **N+1 Problem** — Loading N parent entities triggers N separate queries for children; solve with `JOIN FETCH` in JPQL or `@EntityGraph`.
- **`@Transactional`** — Wraps a method in a database transaction; if an unchecked exception is thrown, the transaction rolls back. Defined at the service layer, not the repository.
- **Spring Data JPA** — Extend `JpaRepository<Entity, ID>` to get `save()`, `findById()`, `findAll()`, `delete()`, and paginated queries for free — zero implementation code needed.
- **Derived Query Methods** — Spring generates SQL from method name: `findByEmailAndActiveTrue()`, `findByCreatedAtAfterOrderByNameAsc()`.
- **`@Query`** — Write JPQL or native SQL directly when the derived method name would be unreadably long or when you need joins.
- **Pagination** — `findAll(Pageable pageable)` returns a `Page<T>` with content, total elements, and total pages; pass `PageRequest.of(page, size, Sort.by("name"))`.
- **Database Migrations (Flyway / Liquibase)** — Version-controlled, automated schema migration; Flyway applies SQL scripts (`V1__create_users.sql`) in order at startup; Liquibase uses XML/YAML changelogs. Never modify a deployed schema by hand — always use migrations.

---

### 🔷 Spring Security Basics

- **Authentication vs Authorization** — Authentication: verifying *who* you are (identity check). Authorization: verifying *what* you're allowed to do (permission check). These are separate concerns and should be treated that way.
- **`SecurityFilterChain`** — The configuration bean that defines which URLs require authentication, which are public, and which authentication mechanism to use (form login, JWT, OAuth2).
- **JWT (JSON Web Token)** — A compact, self-contained token with encoded user claims and a digital signature; the server validates the signature without needing a session store — ideal for stateless REST APIs.
- **OAuth2 / OpenID Connect** — Standard protocols for delegating authentication to an external provider (Google, Keycloak, Okta); `spring-boot-starter-oauth2-resource-server` handles JWT validation automatically.
- **`@PreAuthorize`** — Method-level security; `@PreAuthorize("hasRole('ADMIN')")` rejects calls before the method body executes; requires `@EnableMethodSecurity`.
- **Password Encoding** — Never store plain-text passwords; use `BCryptPasswordEncoder` — it's slow by design (makes brute-force attacks expensive).
- **CSRF Protection** — Cross-Site Request Forgery protection; disable for stateless REST APIs using JWT but keep it enabled for session-based web apps.

---

### 🔷 Typical Request-Response Flow

Here's what actually happens between a client sending a request and receiving a response:

```
Client (Browser / Mobile App)
        │
        ▼
  ┌───────────────────────────────────┐
  │  Servlet Filter Chain             │  ← Security filters, CORS, logging, rate limiting
  └───────────────────────────────────┘
        │
        ▼
  ┌───────────────────────────────────┐
  │  DispatcherServlet                │  ← Spring MVC's front controller; routes to handler
  └───────────────────────────────────┘
        │
        ▼
  ┌───────────────────────────────────┐
  │  HandlerInterceptor (preHandle)   │  ← Auth checks, request logging, timing
  └───────────────────────────────────┘
        │
        ▼
  ┌───────────────────────────────────┐
  │  @RestController                  │  ← Parse input, validate (@Valid), call service
  └───────────────────────────────────┘
        │  (only delegates — zero business logic here)
        ▼
  ┌───────────────────────────────────┐
  │  @Service                         │  ← All business rules, @Transactional, orchestration
  └───────────────────────────────────┘
        │
        ▼
  ┌───────────────────────────────────┐
  │  @Repository / Spring Data JPA    │  ← Database interaction; no business logic
  └───────────────────────────────────┘
        │
        ▼
  ┌───────────────────────────────────┐
  │  Database (MySQL / PostgreSQL)    │  ← SQL execution; results travel back up
  └───────────────────────────────────┘
        │
        ▼  (Response travels back up through each layer)
  [ JSON Response with HTTP status ]
        │
        ▼
       Client
```

**Hard rule on layer responsibilities:**
- **Controller** — HTTP concerns only. Parse input, call service, return response. If you write business logic here, stop.
- **Service** — Business logic lives here. Nothing else. `@Transactional` goes here.
- **Repository** — Database access only. No business logic, no HTTP awareness.

---

## 5. Microservices & Communication

> ⚠️ **Important warning before you start:** Microservices are not an upgrade — they are a tradeoff. They introduce enormous operational complexity: distributed tracing, eventual consistency, network failures, multiple deployments. The vast majority of applications are better served by a well-structured monolith. Only adopt microservices when you have a clear, specific reason — usually independent scaling needs or very large team sizes. Learn the patterns, but don't apply them prematurely.

### 🔷 Core Microservices Concepts

- **Microservices Architecture** — Decompose an application into small, independently deployable services that each own their data and a single business capability (e.g., one service for orders, one for payments, one for inventory).
- **Database per Service** — Each service owns its own database schema; no shared tables across service boundaries. Enforces loose coupling at the data layer.
- **API Gateway** — A single public entry point that routes requests to downstream services; centralizes authentication, rate limiting, SSL termination, and request logging. Tool: `Spring Cloud Gateway`.
- **Service Registry & Discovery** — Services register themselves on startup and look each other up by name — no hardcoded URLs or IPs. Tools: `Netflix Eureka`, `Consul`. Client: `@EnableEurekaClient`.
- **Load Balancer** — Distributes traffic across multiple instances of the same service; prevents any single instance from being overwhelmed. Tool: `Spring Cloud LoadBalancer` (replaced Ribbon).
- **Circuit Breaker** — The single most important resilience pattern; stops sending requests to a failing service and returns a fallback response, preventing cascading failures across the system. States: `CLOSED` (normal) → `OPEN` (failing) → `HALF_OPEN` (testing recovery). Tool: `Resilience4j`.
- **Retry & Timeout** — Automatically retry transient failures with exponential backoff; always set timeouts on cross-service calls — a slow downstream service will eventually exhaust your thread pool.
- **SAGA Pattern** — Manages distributed transactions across services without a two-phase commit; two flavors:
  - *Choreography SAGA* — Each service publishes an event after completing its step; the next service listens and reacts. No central coordinator; simpler to start with.
  - *Orchestration SAGA* — A dedicated SAGA orchestrator tells each service what to do step by step; easier to reason about failure flows and compensating transactions.
- **CQRS (Command Query Responsibility Segregation)** — Separate your write model (commands) from your read model (queries); optimize each independently for better scalability and performance.
- **Event Sourcing** — Store state changes as an immutable sequence of events rather than only the current state; the current state is derived by replaying events.
- **Outbox Pattern** — Reliably publish events by writing them to a database "outbox" table within the same local transaction as your business data; a separate process polls and publishes — guarantees at-least-once delivery.
- **Idempotency** — Design write operations so calling them multiple times produces the same result as calling once; critical for safe retry logic (use idempotency keys in requests).
- **Distributed Tracing** — Track a single request as it hops across multiple services using a `traceId` that propagates in headers; without this, debugging production issues is nearly impossible. Tools: `Zipkin`, `Jaeger`, `Spring Cloud Sleuth` / Micrometer Tracing.
- **Centralized Logging** — Aggregate logs from all service instances into one searchable store; structure logs as JSON and always include `traceId` and `spanId`. Stack: ELK (Elasticsearch + Logstash + Kibana) or Splunk.
- **Metrics & Alerting** — Measure service health (request rate, error rate, p99 latency); set alerts on error rate and latency thresholds. Stack: `Micrometer` + `Prometheus` + `Grafana`.
- **Spring Cloud Config** — Centralized, externalized configuration for all microservices in one Git repository; services fetch config at startup and can refresh without restarting.

---

### 🔷 Synchronous Communication

Use synchronous communication when you need an immediate response.

**RestTemplate** *(Legacy — still very common in existing code)*
- Traditional blocking HTTP client; calling thread waits for the full response.
- Being deprecated in favor of `WebClient` in new code — but you'll encounter it in every existing codebase.

```java
RestTemplate restTemplate = new RestTemplate();
String result = restTemplate.getForObject(
    "http://inventory-service/items/{id}", String.class, itemId);
```

**FeignClient** *(Preferred for microservice-to-microservice calls)*
- Declarative REST client; write an interface annotated with Spring MVC annotations, Feign generates the HTTP implementation.
- Integrates naturally with Eureka (use service name, not URL), Resilience4j (circuit breaker), and Spring Cloud LoadBalancer.

```java
@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/items/{id}")
    ItemDTO getItem(@PathVariable Long id);
}
```

**WebClient** *(Modern — non-blocking, from Spring WebFlux)*
- Non-blocking, reactive HTTP client; the calling thread is not held while waiting for a response.
- Use when you have high concurrency requirements or are already in a reactive stack.
- Also works fine in non-reactive Spring MVC apps when you call `.block()` — though that's a code smell in a fully reactive context.

---

### 🔷 Asynchronous Communication

Use asynchronous communication when the caller doesn't need an immediate response, or when you want to decouple services from each other's availability.

**RabbitMQ**
- A traditional message broker using the AMQP protocol; messages are pushed to consumers and deleted after acknowledged consumption.
- Core model: **Producer → Exchange → Queue → Consumer**; the Exchange routes messages to queues based on routing keys or topic patterns.
- Spring: `@RabbitListener(queues = "orders")` for consumers; `RabbitTemplate.convertAndSend()` for producers.
- Best for: Task queues, work distribution, request-reply (RPC) patterns, lower-throughput messaging.
- **Message durability** — Declare queues and exchanges as durable, and messages as persistent; they survive a broker restart.

**Apache Kafka**
- A distributed event streaming platform; events are written to immutable, ordered topic logs and retained for a configurable period (days, weeks, forever).
- Core model: **Producer → Topic → Partition → Consumer Group**; each partition is an append-only log; ordering is guaranteed within a partition.
- Spring: `@KafkaListener(topics = "order-placed")` for consumers; `KafkaTemplate.send("order-placed", event)` for producers.
- Best for: High-throughput event streaming, event sourcing, audit logs, real-time data pipelines, event-driven architectures.
- **Consumer Groups** — Consumers in the same group share partitions (each event processed once per group); different groups each get a full independent copy of every event.
- **Offset management** — Kafka tracks where each consumer group is in each partition; prefer manual offset commit for exactly-once-style processing control.

| Feature | RabbitMQ | Kafka |
|---|---|---|
| Message retention | Deleted after consumption | Retained (configurable TTL) |
| Throughput | Moderate | Very high (millions/sec) |
| Ordering | Per-queue | Per-partition |
| Multiple consumers | Competing (one processes each message) | Fan-out (each group gets all messages) |
| Best use case | Task queues, RPC, simple decoupling | Event streaming, audit logs, high-volume |
| Protocol | AMQP | Kafka binary protocol |

> Rule of thumb: If you're unsure which to use, start with RabbitMQ. It's simpler to operate and sufficient for most use cases. Move to Kafka when you actually need retention, fan-out, or high throughput.

---

## 6. Deployment & Tools

> Writing great code is half the job. Shipping it reliably at scale is the other half. These aren't optional extras — they're core to being a hireable developer in 2025+.

### 🔷 Git (Non-negotiable foundation)

Learn this before everything else in this section. None of the other tools matter if you can't manage your code properly.

- **Core workflow** — `git add` → `git commit` → `git push`; `git pull` to fetch changes; `git checkout -b feature/my-feature` to branch.
- **Branching strategy** — Feature branches, Pull Requests, code review before merge; keep `main`/`master` always deployable.
- **`git rebase` vs `git merge`** — Merge preserves full history; rebase rewrites history for a cleaner linear log; know the difference and your team's convention.
- **`.gitignore`** — Always ignore compiled output (`target/`, `build/`), IDE files (`.idea/`, `.vscode/`), and secrets (`.env`).

---

### 🔷 Docker

- **What it solves** — "Works on my machine" dies with Docker; your Spring Boot JAR + JDK + config becomes one portable container image that runs identically everywhere.
- **Dockerfile** — A script that defines how to build your image: start from a base JDK image, copy your JAR, set the entrypoint.
- **`docker build` & `docker run`** — Build an image from a Dockerfile; run a container from that image with port mapping (`-p 8080:8080`).
- **Docker Compose** — Define and run multi-container applications (Spring Boot + PostgreSQL + Redis + Kafka) with a single `docker-compose.yml`; essential for local development.
- **Multi-stage builds** — Compile in one stage using a JDK image, copy only the final JAR into a smaller JRE runtime image; significantly reduces the image size and attack surface.
- **Container Registry** — Store and share Docker images; Docker Hub (public), Amazon ECR (AWS private), GitHub Container Registry.

```dockerfile
# Multi-stage Dockerfile for Spring Boot
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

---

### 🔷 Kubernetes (K8s)

- **What it is** — An orchestration platform that automates deploying, scaling, and managing containerized applications in production.
- **Why it matters** — Docker runs one container. Kubernetes runs thousands, self-heals failures, scales on demand, and manages zero-downtime rollouts.
- **Pod** — The smallest deployable unit; wraps one or more containers sharing network and storage.
- **Deployment** — Manages a desired number of identical Pod replicas; handles rolling updates and automatic rollbacks on failure.
- **Service** — A stable network endpoint for a set of Pods; provides internal load balancing and DNS-based service discovery.
- **ConfigMap & Secret** — Externalize `application.properties` values (ConfigMap) and sensitive credentials (Secret) from your container image; inject at runtime.
- **Ingress** — Cluster-level HTTP routing; directs external traffic to the right Service based on host/path rules — the K8s equivalent of an API Gateway.
- **Liveness & Readiness Probes** — K8s polls `/actuator/health`; liveness failures trigger a pod restart, readiness failures remove the pod from the load balancer temporarily.
- **HPA (Horizontal Pod Autoscaler)** — Automatically scales replica count based on CPU/memory usage or custom metrics.
- **Namespaces** — Logical partitions within a cluster; isolate `dev`, `staging`, and `prod` environments or separate teams' workloads.

---

### 🔷 AWS Essentials for Spring Boot

| Service | What it is | Spring Boot use case |
|---|---|---|
| **EC2** | Virtual machines | Run Spring Boot JAR on a managed VM; you manage OS and runtime |
| **ECS** | Managed container runtime | Run Docker containers without managing Kubernetes |
| **EKS** | Managed Kubernetes | K8s without managing the control plane |
| **RDS** | Managed relational database | Production MySQL/PostgreSQL with automated backups and failover |
| **S3** | Object storage | Store file uploads, static assets, backups, and logs |
| **ElastiCache** | Managed Redis/Memcached | Caching layer to reduce DB load and improve response times |
| **SQS** | Managed message queue | Simpler RabbitMQ alternative for decoupled async processing on AWS |
| **MSK** | Managed Kafka | Kafka without the operational burden of self-hosting |
| **ECR** | Private container registry | Store and pull Docker images for ECS/EKS deployments |
| **IAM** | Identity and access management | Least-privilege roles for all services; never use root credentials |
| **CloudWatch** | Monitoring and logging | Aggregate logs, set alarms, and visualize metrics from all services |

---

### 🔷 CI/CD

Automate everything that can be automated. Manual deployment is a liability.

- **Jenkins** — Open-source automation server; define pipelines as code in a `Jenkinsfile`; highly customizable but requires maintenance.
- **GitHub Actions** — Cloud-native CI/CD built into GitHub; trigger workflows on push or PR with `.github/workflows/*.yml`; zero infrastructure to manage.
- **Typical pipeline stages** — Code push → Build (`mvn package`) → Unit Tests → Code Quality (`SonarQube`) → Docker Build → Push to Registry → Deploy to K8s/ECS.
- **SonarQube** — Static analysis for code quality, test coverage, and security vulnerabilities; blocks the pipeline if quality gates aren't met.

---

### 🔷 Build Tools

- **Maven** — XML-based build tool; `pom.xml` manages dependencies and build lifecycle phases (`validate` → `compile` → `test` → `package` → `verify` → `install` → `deploy`).
- **Gradle** — Groovy/Kotlin DSL build tool; more flexible and faster (incremental builds, caching) than Maven for large projects.
- **`mvn clean install`** — Deletes previous output, compiles sources, runs all tests, and installs the JAR into your local `.m2` repository.
- **`mvn spring-boot:run`** — Builds and runs your Spring Boot application directly from Maven without first creating a JAR.

---

## 7. Incorporating AI into Spring Boot

> AI integration is now a standard expected skill for Spring Boot developers — and it's far simpler than most people think. For most business use cases, you're just calling an external API.

### 🔷 Spring AI

- **What it is** — The official Spring project for integrating LLMs and AI services into Spring Boot applications with a familiar, Spring-idiomatic API; vendor-neutral — one API that works across providers.
- **Supported providers** — OpenAI (GPT-4o), Anthropic (Claude), Azure OpenAI, Google Vertex AI, Ollama (local models running on your machine), Mistral.
- **`ChatClient`** — The core abstraction; call any LLM with a prompt and get a response, the same way you'd call a `RestTemplate`.
- **Prompt Templates** — Parameterized, reusable prompt strings managed as Spring beans; keeps your AI prompts testable and versioned.
- **Vector Stores** — Store text embeddings for semantic similarity search; Spring AI integrates with PGVector (PostgreSQL), Redis, Chroma, Pinecone, Milvus.
- **RAG (Retrieval-Augmented Generation)** — Enhance LLM responses with your own data: embed your documents into a vector store, retrieve the most relevant ones at query time, and inject them into the LLM prompt as context.
- **Structured Output** — Map LLM responses directly to Java POJOs using Spring AI's `BeanOutputConverter`; no manual JSON parsing.

```java
@Autowired
private ChatClient chatClient;

public String askQuestion(String question) {
    return chatClient.prompt()
        .user(question)
        .call()
        .content();
}
```

---

### 🔷 LangChain4j (Alternative to Spring AI)

- **What it is** — A Java port of the popular Python LangChain framework; more mature than Spring AI in some areas with agents, tools, conversation memory, and full RAG pipelines.
- **AI Services** — Define an interface and annotate it; LangChain4j generates the LLM-backed implementation — the "FeignClient of AI."
- **When to choose it over Spring AI** — When you need agents with tool-calling, long-term conversation memory, or more advanced RAG pipelines.

---

### 🔷 Calling AI APIs Directly (Without a Framework)

For simple use cases, frameworks are optional — a direct `WebClient` call works fine:

```java
// Calling OpenAI directly using WebClient
webClient.post()
    .uri("https://api.openai.com/v1/chat/completions")
    .header("Authorization", "Bearer " + apiKey)
    .bodyValue(requestBody)
    .retrieve()
    .bodyToMono(OpenAIResponse.class);
```

---

### 🔷 Practical AI Use Cases in Spring Boot

- **Semantic search** — Embed product descriptions or articles into a vector store; users search by meaning, not keywords.
- **Contextual chatbot (RAG)** — A customer support bot that answers questions from your actual documentation, not just the LLM's training data.
- **Document summarization** — Accept a PDF or text block via REST endpoint, send to an LLM, return a structured summary.
- **Sentiment analysis** — Classify user feedback or reviews at scale by calling a classification model.
- **Structured data extraction** — Parse unstructured text (emails, scanned forms) into Java POJOs.
- **Log anomaly detection** — Feed structured log data to a model to detect unusual patterns before they become incidents.

**Production considerations:**
- LLMs are slow (latency 1–10 seconds) — never make a synchronous LLM call on a request thread; use `CompletableFuture` or `WebClient` for non-blocking calls.
- Track token usage — LLM API costs are based on token count; a bug can generate a massive bill overnight.
- Rate limiting and retry — Implement exponential backoff with jitter; LLM APIs have rate limits that will be hit in production.
- Keep model inference and business logic strictly separated — treat ML as a service layer and version models independently of your application.

---

## 8. Additional Resources

> The map is not the territory. Build things. These resources fill the gaps.

### 🔷 Books

| Book | Why Read It |
|---|---|
| *Effective Java* (3rd Ed.) by Joshua Bloch | The bible of writing idiomatic, robust Java. Read one chapter per month for the rest of your career. |
| *Spring in Action* (6th Ed.) by Craig Walls | The definitive, practical Spring Boot reference book. |
| *Clean Code* by Robert C. Martin | Teaches you to write code that humans can actually read and maintain. |
| *Java Concurrency in Practice* by Brian Goetz | The definitive reference for multithreading, synchronization, and performance. Non-negotiable for senior roles. |
| *Designing Data-Intensive Applications* by Martin Kleppmann | Essential for understanding distributed systems, databases, Kafka, and data flow. |
| *Microservices Patterns* by Chris Richardson | Deep dives on SAGA, CQRS, API Gateway, Outbox, and transactional messaging. |
| *Building Microservices* (2nd Ed.) by Sam Newman | Read this before you build microservices; saves you from expensive architectural mistakes. |
| *Cloud Native Spring in Action* by Thomas Vitale | Covers microservices, resilience, configuration, and cloud deployment with modern Spring. |
| *The Pragmatic Programmer* by Hunt & Thomas | Timeless craft and career wisdom; not Java-specific but every sentence applies. |

---

### 🔷 YouTube Channels

| Channel | Best For |
|---|---|
| **Java Brains** | Spring Boot, Spring Security, Microservices — beginner-friendly, conceptually deep |
| **Amigoscode** | Full project walkthroughs: Spring Boot, Docker, PostgreSQL, Security |
| **in28minutes** | Structured Spring Boot, Microservices, AWS, Docker courses |
| **TechWorld with Nana** | Docker, Kubernetes, CI/CD — the best DevOps channel for developers |
| **Dan Vega** | Spring Boot 3 tutorials, Spring AI, practical modern Spring — highly recommended |
| **Josh Long (Spring Tips)** | Practical, cutting-edge tips from an original Spring developer; always worth watching |
| **Daily Code Buffer** | Spring Boot projects, microservices, interview prep |
| **Defog Tech** | Java concurrency and JVM internals explained with exceptional clarity |
| **Spring Developer (Official)** | Official Spring channel — release deep-dives, best practices, framework internals |
| **Telusko** | Beginner-friendly Java + Spring Boot playlists |

---

### 🔷 Interview Preparation

- **Baeldung** (`baeldung.com`) — The most reliable Spring Boot reference on the internet; searchable, thorough, code-first. Bookmark it now.
- **Daily Code Buffer — Spring Boot Interview Questions** — The most accurate list of real-world interview questions you'll actually be asked.
- **LeetCode** — Focus on Easy/Medium problems; Top 75 (NeetCode list) covers 90% of what you'll face in coding rounds.
- **NeetCode.io** — Structured roadmap with pattern groupings and video explanations; better than randomly grinding LeetCode.
- **System Design Primer** (`donnemartin/system-design-primer` on GitHub) — Essential for mid-level and senior system design rounds.
- **ByteByteGo / Grokking the System Design Interview** — Visual system design concepts (API gateways, caching, DB scaling, message queues).
- **Spring Official Guides** (`spring.io/guides`) — Short, focused, official tutorials that teach by doing; REST, JPA, Security, Messaging.
- **JavaGuides** (`javaguides.net`) — Practical Spring Boot annotated examples with interview questions at the end of each topic.

---

### 🔷 Practice Platforms

- **Spring Initializr** (`start.spring.io`) — Generate any Spring Boot project in seconds; the right starting point every time.
- **GitHub** — Build a portfolio: one CRUD app, one microservices project, one with Kafka or RabbitMQ, one with Docker + CI/CD.
- **Postman / Insomnia** — Test and document your REST APIs interactively; use Postman Collections to automate test suites.
- **Docker Hub** — Pull and experiment with official images (MySQL, Redis, Kafka, RabbitMQ, Elasticsearch) for local development.
- **HackerRank** — Java track with structured challenges; good for warming up Java syntax if you're rusty.
- **Exercism** — Java track with mentor feedback; excellent for getting a second opinion on your code style.

---

### 🔷 Communities

- **Stack Overflow** — When you're stuck; search before asking; tags `spring-boot`, `java`, `hibernate` are very active.
- **Reddit** — `r/java`, `r/learnjava`, `r/microservices`; real practitioners sharing real problems.
- **Spring Blog** (`spring.io/blog`) — Official announcements, release notes, and deep-dive articles from the Spring team.
- **Dev.to** — Practical community tutorials; good for intermediate topics and learning from other practitioners.

---

### 🔷 Hands-On Project Ideas

Theory alone won't make you proficient. Here are progressively harder projects:

1. **CRUD REST API** — Spring Boot + Spring Data JPA + PostgreSQL + Flyway migrations + `@Valid` input validation + error handling.
2. **Secured API** — Add JWT authentication + Spring Security + role-based authorization (`ADMIN`, `USER`) to project 1.
3. **Microservices Backend** — Three services (e.g., Order, Inventory, Notification) wired with Eureka, Spring Cloud Gateway, Feign, and Resilience4j circuit breakers.
4. **Event-driven system** — Add Kafka or RabbitMQ to project 3; implement the Outbox Pattern for reliable event publishing.
5. **Containerized deployment** — Write a Dockerfile and `docker-compose.yml` for project 1; add a GitHub Actions pipeline that runs tests and builds the image.
6. **AI-enhanced feature** — Add a Spring AI–powered semantic search or document summarization endpoint to any of the above.

---

## 9. Common Mistakes to Avoid

These are the patterns that slow developers down, create tech debt, and fail interviews. Learn them now, not in production.

1. **Skipping Java fundamentals to get to Spring Boot faster** — You'll hit a wall within weeks. Spring is just Java — without strong Java, you can't debug Spring.
2. **Writing business logic in Controllers** — Controllers should be thin HTTP adapters. The moment you write an `if` statement about a business rule in a Controller, you're doing it wrong.
3. **Using `EAGER` fetching everywhere** — It feels convenient until you load 10,000 related entities by accident. Default to `LAZY` and use `JOIN FETCH` or `@EntityGraph` when you need the data.
4. **Ignoring transactions** — Not annotating service methods with `@Transactional` when they modify multiple records is asking for partial updates and data corruption.
5. **Exposing `@Entity` objects directly in API responses** — It leaks your database schema, creates circular serialization issues, and tightly couples your API contract to your DB structure. Always use DTOs.
6. **Skipping database migrations** — Changing a schema by running SQL manually against production is how data gets corrupted and teams lose trust. Use Flyway or Liquibase from day one.
7. **Building microservices before understanding monoliths** — A microservices architecture built by someone who can't build a good monolith is ten times worse than a monolith. Earn the right to use microservices.
8. **Not writing tests** — "I'll write tests later" means never. Write tests as you go; at minimum, unit test your Service layer.
9. **Adding every Spring Boot starter you see** — Every unused dependency is a security surface and a source of auto-configuration conflicts. Only add what you actually use.
10. **Hardcoding secrets** — API keys, database passwords, and tokens do not belong in `application.properties` committed to Git. Use environment variables, Spring Cloud Config, or a secrets manager.
11. **Copying code you don't understand** — Stack Overflow is a tool, not a substitute for understanding. If you paste code you can't explain, you own a bug you can't debug.

---

## 10. Suggested Learning Path

> This path takes approximately 6 months of consistent, focused work to reach a hireable junior level. There are no shortcuts — but there is a right order.

```
Month 1–2:   Java Foundations
             → OOP, SOLID, Collections, Generics, Streams, Lambdas
             → Multithreading basics (Executor, CompletableFuture)
             → Exception handling, JDBC basics
             → JUnit 5 + Mockito

Month 2–3:   Spring Core + Spring Boot
             → IoC/DI, Bean scopes, AOP
             → Spring MVC (REST controllers, validation, error handling)
             → Spring Data JPA + Hibernate (entities, relationships, LAZY/EAGER)
             → Flyway migrations
             → Build: a complete CRUD REST API

Month 3–4:   Security + Testing + Configuration
             → Spring Security (JWT, roles, @PreAuthorize)
             → @ConfigurationProperties, Spring Profiles, externalized config
             → @SpringBootTest, @WebMvcTest, @DataJpaTest
             → Build: add auth to your CRUD API

Month 4–5:   Microservices Fundamentals
             → Service discovery (Eureka), API Gateway (Spring Cloud Gateway)
             → Feign Client, Spring Cloud LoadBalancer
             → Circuit Breaker + Retry (Resilience4j)
             → SAGA pattern (choreography first, then orchestration)
             → Build: 2–3 services communicating via Feign

Month 5–6:   Messaging + Observability
             → RabbitMQ (exchanges, queues, @RabbitListener)
             → Apache Kafka (topics, partitions, consumer groups, @KafkaListener)
             → Outbox Pattern for reliable event publishing
             → Distributed tracing (Micrometer Tracing + Zipkin)
             → Centralized logging (ELK or CloudWatch)

Month 6+:    Deployment + DevOps
             → Docker (Dockerfile, Docker Compose, multi-stage builds)
             → Kubernetes basics (Pod, Deployment, Service, ConfigMap, Probes)
             → AWS (EC2/ECS/EKS, RDS, S3, SQS)
             → CI/CD (GitHub Actions pipeline end-to-end)

Ongoing:     → DSA practice: 2–3 LeetCode problems/week (Easy → Medium)
             → Build one end-to-end project that uses everything above
             → Read one chapter of Effective Java per month
             → Follow spring.io/blog for what's changing
```

---

> *"The expert in anything was once a beginner."* — Helen Hayes
>
> Stick to the path. Build projects. Break things deliberately. Fix them. Understand *why* they broke. That's how this works.

---

*Last updated: June 2026 | Targets Spring Boot 3.x + Java 17/21 | Contributions welcome*
