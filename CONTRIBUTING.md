# Contributing Guidelines

Happy to have you here!

You can contribute by submitting [issues][issue], starting a [discussion][discussion] or raising [PRs][pr].

You are welcome to raise bugs and/or feature requests!

If you want to submit a PR that is great too, but make sure it is discussed first.

The project follows the [Kotlin coding conventions][kotlin-code-conventions].

## Code coverage :white_check_mark:
Your change should be accompanied by the necessary tests. Your changes should not affect the code coverage percentage.

The code coverage is calculated everytime a PR is raised. You can also check for it by running the following command:

```shell
./gradlew kovibes:koverVerify
```

## Running the examples :arrow_forward:
If you want to check your changes with the help of examples, make sure you register an app on Spotify.

You can follow the [Spotify documentation][spotify-new-app-doc] to create an app.

Once you have setup your app, make sure to set your `Client ID` and `Client Secret` in your local.properties as `client.id` and `client.secret`.


[issue]: https://github.com/rubenquadros/KoVibes/issues
[discussion]: https://github.com/rubenquadros/KoVibes/discussions
[pr]: https://github.com/rubenquadros/KoVibes/pulls
[spotify-new-app-doc]: https://developer.spotify.com/documentation/web-api/tutorials/getting-started#:~:text=of%20your%20choice.-,Set%20Up%20Your%20Account,-Login%20to%20the
[kotlin-code-conventions]: https://kotlinlang.org/docs/coding-conventions.html