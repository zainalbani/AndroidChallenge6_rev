package com.example.challenge6.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.challenge6.databinding.ActivityLoginBinding
import com.example.challenge6.home.HomeActivity
import com.example.challenge6.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.textViewLinkRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            viewModel.auth(
                binding.textInputEditTextEmail.text.toString(),
                binding.textInputEditTextPassword.text.toString()
            )
            viewModel.user.observe(this) {
                if (it != null) {
                    viewModel.saveIsLoginStatus(true)
                    viewModel.saveUsername(it.username.toString())
                    viewModel.saveId(it.id!!.toInt())
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getDataStoreIsLogin().observe(this) {
            if (it == true) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}