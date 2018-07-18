# CoroutinesExpo
Sample project depicting the use of Coroutines in Android networking. The base usage of coroutines is shown, for creating an
async scope, pushing the request into a suspension function and returning it back to UI scope. Additionally a way to test the
request/method is also shown, by using a CoroutineContext provider, allowing us to force the test into threads we want.

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
This idea came from a combination of multiple ideas, throughout time. As it's a simple implementation and many people have
tackled the same problem on their own, I hold no copyright or ownership of the said implementation. The final API came out
through a friendly debate between my peers and myself. I haven't invented anything new, I just had a problem and solved it
step by step, by both coming up with things and Googling out the issues at hand.
