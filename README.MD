![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/lmbotero/PullToRefresh/code_style.yml?branch=main&label=Check%20code%20style)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/lmbotero/PullToRefresh/build.yml?branch=main&label=Build%20multiplatform%20project)
![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/lmbotero/PullToRefresh/wiki.yml?branch=main&label=Publish%20wiki)
![GitHub Release](https://img.shields.io/github/v/release/lmbotero/PullToRefresh)
![GitHub License](https://img.shields.io/github/license/lmbotero/PullToRefresh)

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

## 🚀 Basic Usage

Wrap your composable content inside `PullToRefreshLayout`:

```kotlin
PullToRefreshLayout(
    isRefreshing = isLoading,
    onRefresh = { /* Execute your pull-to-refresh task */ },
) {
    // Your composable content here
}
```

## 🎨 Customization

### Modify Threshold & State

You can customize the refresh threshold and state using `rememberPullToRefreshConfig`.

```kotlin
val config = rememberPullToRefreshConfig(
    enabled = true,
    threshold = 60.dp
)

PullToRefreshLayout(
    isRefreshing = isLoading,
    onRefresh = { /* execute your pull-to-refresh task */ },
    config = config
) {
    // Your composable content here
}
```

### Custom Refresh Indicator

Override the default indicator using the `indicator` parameter.

See https://developer.android.com/reference/kotlin/androidx/compose/material3/pulltorefresh/package-summary.html
to see the official material3 `pull-to-refresh` reference

## 📚 API Reference

### PullToRefreshLayout

```kotlin
@Composable
fun PullToRefreshLayout(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    config: PullToRefreshConfig = rememberPullToRefreshConfig(),
    state: PullToRefreshState = rememberPullToRefreshState(),
    indicator: @Composable BoxScope.() -> Unit = {
        DefaultIndicator(
            state = state,
            isRefreshing = isRefreshing,
        )
    },
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
)
```

| Parameter          | Type                              | Description                                                 |
|--------------------|-----------------------------------|-------------------------------------------------------------|
| `isRefreshing`     | `Boolean`                         | Whether the content is currently refreshing                 |
| `onRefresh`        | `() -> Unit`                      | A function to execute when the content is pulled to refresh |
| `modifier`         | `Modifier`                        | Modifier for the layout                                     |
| `config`           | `PullToRefreshConfig`             | Configuration for the pull-to-refresh                       |
| `state`            | `PullToRefreshState`              | State for the pull-to-refresh                               |
| `indicator`        | `@Composable BoxScope.() -> Unit` | Composable loading indicator                                |
| `contentAlignment` | `Alignment`                       | Alignment for the content                                   |
| `content`          | `@Composable BoxScope.() -> Unit` | Composable content                                          |

### PullToRefreshConfig

```kotlin
data class PullToRefreshConfig(
    val enabled: Boolean = true,
    val threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
)
```

### rememberPullToRefreshConfig

```kotlin
@Composable
fun rememberPullToRefreshConfig(
    enabled: Boolean = true,
    threshold: Dp = PullToRefreshDefaults.PositionalThreshold,
): PullToRefreshConfig
```

- Returns: A `PullToRefreshConfig` instance with saved state.

## 📜 License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## 🚀 Happy coding!

This library simplifies pull-to-refresh for Compose Multiplatform, making it easy to use and
customize! 💙
