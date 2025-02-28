import androidx.compose.ui.window.ComposeUIViewController
import io.github.lmbotero.pulltorefresh.sample.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
