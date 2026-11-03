package com.example.activitylifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate")
    setContent {
      MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
          Column(
            modifier = Modifier.safeDrawingPadding().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(stringResource(R.string.app_name), style = MaterialTheme.typography.headlineMedium)
            Text(stringResource(R.string.instructions), modifier = Modifier.padding(top = 16.dp))
          }
        }
      }
    }
  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart")
  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume")
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop")
  }

  override fun onRestart() {
    super.onRestart()
    Log.d(TAG, "onRestart")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy")
  }

  companion object {
    private const val TAG = "ActivityLifecycle"
  }
}
