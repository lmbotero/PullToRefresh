# Compose Multiplatform PullToRefresh

A simple and lightweight Compose Multiplatform library that implements Material3-based
pull-to-refresh functionality for JVM, Android, and iOS platforms.

## Features

- **Material 3 Pull-to-Refresh**: Fully adheres to Material 3 design principles.
- **Multiplatform Support**: Works seamlessly on Android, iOS, and JVM.
- **Mouse Support for JVM**: Enables pull-to-refresh via mouse click and drag.
- **Customizable Indicator**: Easily style the refresh indicator to match your app's theme.

## Installation

Add the dependency to your `build.gradle.kts` (or `build.gradle` for Groovy users):

```kotlin
implementation("com.lumbot.pulltorefresh:version")
```

## Basic Usage

To use PullToRefreshLayout, wrap your composable content within it:

```
PullToRefreshLayout(
    isRefreshing = isLoading,
    onRefresh = { // execute pull-to-refresh task },
) {
    // Your composable content here
}
```

## Customization

You can customize the refresh indicator using Material3's styling options.
See https://developer.android.com/reference/kotlin/androidx/compose/material3/pulltorefresh/package-summary.html
for reference

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

Happy coding! 🚀
