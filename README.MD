![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/lmbotero/PullToRefresh/code_style.yml?branch=main&label=Check%20code%20style)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/lmbotero/PullToRefresh/build.yml?branch=main&label=Build%20multiplatform%20project)
![GitHub License](https://img.shields.io/github/license/lmbotero/PullToRefresh)
![GitHub Release](https://img.shields.io/github/v/release/lmbotero/PullToRefresh)

# Compose Multiplatform PullToRefresh

A simple and lightweight Compose Multiplatform library that implements Material3-based
pull-to-refresh functionality for JVM, Android, and iOS platforms.

## ✨ Features

- **Material 3 Pull-to-Refresh**: Built following Material 3 design principles.
- **Multiplatform Support**: Works seamlessly across Android, iOS, and JVM.
- **Mouse Support for JVM**: Allows pull-to-refresh using mouse click and drag.
- **Customizable Indicator**: Easily style or replace the default refresh indicator.

## 📦 Installation

Add the dependency to your `build.gradle.kts` (or `build.gradle` for Groovy users):

```kotlin
implementation("io.github.lmbotero:pull-to-refresh:<latest-version>")
```

or using version catalog:

```kotlin
[versions]
pullToRefreshVersion = "<latest-version>"

[libraries]
pullToRefresh = { module = "io.github.lmbotero:pull-to-refresh", version.ref = "pullToRefreshVersion" }
```

Then add the dependency to your `build.gradle.kts` into the `common dependencies` block as follows:

```kotlin
commonMain.dependencies {
    implementation(libs.pullToRefresh)
}
```

## 🚀 Basic Usage

Wrap your composable content inside `PullToRefreshLayout`:

```kotlin
PullToRefreshLayout(
    refreshStateEvent =
        RefreshStateEvent(
            isRefreshing = isLoading,
            onRefresh = { /* Fetch data */ },
        ),
) {
    // Your composable content here
}
```

## 🎨 Customization

You can set your own implementation of the loading indicator by passing a custom composable into
```indicator``` parameter.

See https://developer.android.com/reference/kotlin/androidx/compose/material3/pulltorefresh/package-summary.html
to see the official material3 `pull-to-refresh` reference and documentation.

## 📚 API Reference

### PullToRefreshLayout

```kotlin
@Composable
fun PullToRefreshLayout(
    refreshStateEvent: RefreshStateEvent,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
    state: PullToRefreshState = rememberPullToRefreshState(),
    indicator: @Composable BoxScope.() -> Unit = {
        DefaultIndicator(
            state = state,
            isRefreshing = refreshStateEvent.isRefreshing,
        )
    },
    content: @Composable BoxScope.() -> Unit,
)
```

| Parameter           | Type                              | Description                                                               |
|---------------------|-----------------------------------|---------------------------------------------------------------------------|
| `refreshStateEvent` | `RefreshStateEvent`               | Represents the current update state and handles the event when it changes |
| `modifier`          | `Modifier`                        | Modifier for the layout                                                   |
| `enabled`           | `Boolean`                         | Whether the pull-to-refresh is enabled or not                             |
| `threshold`         | `Dp`                              | The distance threshold for triggering the refresh event                   |
| `state`             | `PullToRefreshState`              | State for the pull-to-refresh                                             |
| `indicator`         | `@Composable BoxScope.() -> Unit` | Composable loading indicator                                              |
| `content`           | `@Composable BoxScope.() -> Unit` | Composable content                                                        |

### PullToRefreshConfig

```kotlin
data class RefreshStateEvent(
    val isRefreshing: Boolean,
    val onRefresh: () -> Unit,
)
```

| Parameter      | Type         | Description                                                 |
|----------------|--------------|-------------------------------------------------------------|
| `isRefreshing` | `Boolean`    | Whether the refresh is currently in progress or not         |
| `onRefresh`    | `() -> Unit` | Function to be executed when the refresh event is triggered |

## 📜 License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## 🚀 Happy coding!

This library simplifies pull-to-refresh for Compose Multiplatform, making it easy to use and
customize! 💙
