package com.ertenecati.presentation

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ertenecati.MainViewModel
import com.ertenecati.R
import com.ertenecati.databinding.FragmentSoundBinding
import java.util.*

class SoundFragment : Fragment(R.layout.fragment_sound) {

    private lateinit var binding: FragmentSoundBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.EXTRA_PARTIAL_RESULTS)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        speechRecognizer.setRecognitionListener(object : RecognitionListener {

            override fun onReadyForSpeech(p0: Bundle?) { }

            override fun onBeginningOfSpeech() { }

            override fun onRmsChanged(p0: Float) { }

            override fun onBufferReceived(p0: ByteArray?) { }

            override fun onEndOfSpeech() { }

            override fun onError(p0: Int) { }

            override fun onResults(bundle: Bundle?) {
                val list = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                list?.let { safeList ->
                    for (item in safeList) {
                        if (item.lowercase().contains("ileri")) {
                            viewModel.sendCommand("2")
                            break
                        } else if (item.lowercase().contains("geri")) {
                            viewModel.sendCommand("8")
                            break
                        } else if (item.lowercase().contains("sağ")) {
                            viewModel.sendCommand("6")
                            break
                        } else if (item.lowercase().contains("sol")) {
                            viewModel.sendCommand("4")
                            break
                        } else if (item.lowercase().contains("dur")) {
                            viewModel.sendCommand("5")
                            break
                        }
                    }
                    var lastCommand = "Son komut:"
                    for (item in safeList) {
                        lastCommand = "$lastCommand $item"
                    }
                    binding.tvLastCommand.text = lastCommand
                }
            }

            override fun onPartialResults(bundle: Bundle?) { }

            override fun onEvent(p0: Int, bundle: Bundle?) { }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSoundBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSound.setOnTouchListener { _, motionEvent ->
            binding.btnSound.performClick()
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                speechRecognizer.startListening(speechIntent)
                binding.tvSoundInfo.text = "Dinliyor..."
            }
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                speechRecognizer.stopListening()
                binding.tvSoundInfo.text = "Basılı tut ve komut ver"
            }
            false
        }
    }

}