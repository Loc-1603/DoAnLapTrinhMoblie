package com.truyen.dexreader.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.truyen.dexreader.presentation.theme.DexReaderTheme
import com.truyen.dexreader.utils.LocaleManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun attachBaseContext(newBase: Context) {
    // Áp dụng ngôn ngữ đã lưu trước khi Activity khởi tạo
    val languageType = LocaleManager.getLanguageFromPrefs(newBase)
    val context = LocaleManager.setLocale(newBase, languageType)
    super.attachBaseContext(context)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      DexReaderTheme {
        DexReaderApp(
          modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
        )
      }
    }
  }

  companion object {
    /**
     * Restart MainActivity để áp dụng ngôn ngữ mới
     */
    fun restart(context: Context) {
      val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      }
      context.startActivity(intent)
    }
  }
}