# KoVibes
KoVibes is a Kotlin wrapper for the Spotify Web API.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.rubenquadros/kovibes/0.0.1)](https://central.sonatype.com/artifact/io.github.rubenquadros/kovibes/0.0.1)
![Github Actions](https://github.com/rubenquadros/kovibes/actions/workflows/main.yml/badge.svg?branch=main)
[![codecov](https://codecov.io/github/rubenquadros/KoVibes/graph/badge.svg?token=UJ0687GJ7R)](https://codecov.io/github/rubenquadros/KoVibes)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)

## Usage :computer:
Include the dependency
<details open>
<summary>Android:</summary>
<pre> <code>
implementation("io.github.rubenquadros:kovibes-android:{latest_version}")
</code></pre>
</details>

<details open>
<summary>JVM:</summary>
<pre> <code>
implementation("io.github.rubenquadros:kovibes-jvm:{latest_version}")
</code></pre>
</details>

`KovibesApi` is the entry class.
```kotlin
val spotifyService: SpotifyService = KoVibesApi.createSpotifyService("id", "secret")
```

`id` is the <i>Client ID</i> and `secret` is the <i>Client Secret</i>.

To create your <i>Client ID</i> and <i>Client Secret</i>, please follow the
[Spotify documentation][spotify-new-app-doc].

The <i>Client ID</i> and <i>Client Secret</i> is required for authentication.

`SpotifyService` is the interface which provides all the methods to fetch the Spotify data.

Each API in `SpotifyService` returns a `SpotifyApiResponse`.

```kotlin
sealed interface SpotifyApiResponse<out RESPONSE, out ERROR> {
    //returns when the underlying call to fetch data was successful
    data class Success<RESPONSE>(val result: RESPONSE): SpotifyApiResponse<RESPONSE, Nothing>

    //returns when the underlying call to fetch data was not successful
    data class Error<ERROR>(val body: ERROR): SpotifyApiResponse<Nothing, ERROR>
}
```

Please refer to the [full API reference][kovibes-doc] for the documentation.

Please refer to the [Spotify Web API documentation][spotify-web-api] to know more about the Spotify API.

## Examples :bulb:
All the examples are in the `example` module.

 - [Android example][android-example]
 - [JVM example][jvm-example]

To run the examples, you will have to create your app in the Spotify portal.

Once you have registered your app, you will be be able to see the `Client ID` and the `Client Secret`.

Make sure to set your `Client ID` and `Client Secret` in your local.properties as `client.id` and `client.secret`.

## Future scope :crystal_ball:
I need your help! All feedback is welcome!

At the moment, only a handful of Spotify APIs are present.
The end goal is to have all the Spotify APIs in the library.

- Add more spotify APIs
- Add support for iOS
- Add an iOS example
- Create a logo

## Contributing :hearts:
If you wish to contribute then please read the [contributing guidelines][contribute].


[spotify-new-app-doc]: https://developer.spotify.com/documentation/web-api/tutorials/getting-started#:~:text=of%20your%20choice.-,Set%20Up%20Your%20Account,-Login%20to%20the
[kovibes-doc]: https://rubenquadros.github.io/KoVibes/
[spotify-web-api]: https://developer.spotify.com/documentation/web-api
[android-example]: https://github.com/rubenquadros/KoVibes/tree/main/example/android
[jvm-example]: https://github.com/rubenquadros/KoVibes/tree/main/example/jvm
[contribute]: https://github.com/rubenquadros/KoVibes/tree/main/CONTRIBUTING.md