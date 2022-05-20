package dev.matyaqubov.googletranslateclone

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dev.matyaqubov.googletranslateclone.data.remote.ApiClient
import dev.matyaqubov.googletranslateclone.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        viewModel.translationWord.observe(this) {
            binding.tvTranslated.text = it.data.translations[0].translatedText
        }

        binding.etWord.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                job?.cancel()
                job = lifecycleScope.launchWhenStarted {
                    delay(500)
                    if (p0!!.isNotEmpty())
                    viewModel.translationWord(p0.toString(), "en", "uz")
                }
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            PostViewModelFactory(ApiClient.translateService)
        )[MainViewModel::class.java]
    }
}