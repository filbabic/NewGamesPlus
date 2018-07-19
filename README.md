# NewGames+
My pet project depicting the use of Coroutines in Clean Architecture, using multi-module projects, Koin as a DI solution and much more.

# Result<T>
Result is a monad which allows for two cases to happen, never simultaneously. It's kinda like Either<T>, allowing us to either
receive some data, or throw/dispatch an error. It's generic implementation and extension functions make for a concise API that,
by looks, resembles Rx-like syntax.

# CoroutineContextProvider
The coroutine context provider is a useful construct which will delegate which "context" should we push the coroutine to.
Simply put, it delegates threads and scheduling in our couroutine powered API, by allowing us to listen on both "main" and "io"
pools. At first glance not very useful, but when it comes to testing, it enables us to switch to a test environment for both
main and io pools, allowing them to virtually run on the same thread, making testing of coroutines available.

## Side note
Coroutine base setup with Result<T> monad and contextProviders came from a discussion, research and hacking things together. I did not create or copy a solution, I just merely built up from an idea, whilst helping myself to other people's examples all the same.
  
# Multi-module projects
I'm trying to build a multi-module project, to better fit Google's guidelines, optimize build speed and dependency graph and potentially develop support for InstantApps. Additionally, multi-module projects have a core setup which takes Clean architecture in mind, and as such separates the `domain`, `data` and `device` modules into separate entities, used across the app.

# Lifecycle handling
Since the dawn of time (pretty much since Android's been out), there was a problem with configuration changes in apps. To mitigate this problem I'm using Scoping in my DI setup and Android Architecture Components, which should preserve ViewModel entitites through configuration change events.

# Dependency Injection
Dagger is great. It's fun. Amazing. It just is... Until something breaks, you have to add a new dependency, structure your DI graph a bit differently or you want to scope some modules.

Then it's just a pain in the boilerplate ass. Being a Kotlin fanatic just made me fall in love instantly with `Koin`. It takes virtually 2 lines of code to scope modules, the dependency factories and beans are concise and clean, and the best part, no annotations!

### P.S.
If you're interested in commenting, helping out, testing, or really anything that might help me make this app come to life, be sure to let me know! :]
