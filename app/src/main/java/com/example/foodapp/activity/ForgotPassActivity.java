package com.example.foodapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.JsonInterfaceApi.JsonApi;
import com.example.foodapp.ModelAPI.User;
import com.example.foodapp.R;
import com.example.foodapp.RetrofitApi.UserApi;
import com.example.foodapp.databinding.ActivityForgotPassBinding;
import com.example.foodapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.grpc.Context;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassActivity extends AppCompatActivity {

    private ActivityForgotPassBinding binding;
    private FirebaseAuth mAuth;
    private Context context;
    public static final String TAG = ForgotPassActivity.class.getName();
    private List<User> mListusers;

    private JsonApi jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mListusers = new ArrayList<>();
        loginRetrofit();
        // event
        event();
        mAuth = FirebaseAuth.getInstance();

    }

    private void event() {
        binding.btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numberPhone =binding.edtInputPhone.getText().toString().trim();
                onClickVerify(numberPhone);

              /** if (mListusers == null || mListusers.isEmpty()){
                   return;
               }
               Boolean isHasUser = false ;
               for (User user: mListusers){
                   if (numberPhone.equals(user.getPhone())) {
                       isHasUser = true;
                       break;
                   }
               }
               if (isHasUser == true){
                  // onClickVerify(numberPhone);
               } else {
                   Toast.makeText(ForgotPassActivity.this, "Not is value", Toast.LENGTH_SHORT).show();
               }**/
            }
        });
        binding.imgBackForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onClickVerify(String numberPhone){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(numberPhone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(ForgotPassActivity.this, "Falled", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verifycationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verifycationId, forceResendingToken);
                                goToVerifyActivity(numberPhone,verifycationId);

                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            /**  GotoLogin Activity  **/
                               goToLoginActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(ForgotPassActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void goToLoginActivity(String phoneNumber) {
        Intent intent = new Intent(this, LoginFoodActivity.class);
        intent.putExtra("Phone number", phoneNumber);
        startActivity(intent);
    }
    private void goToVerifyActivity(String phoneNumber, String verificationId) {
        Intent intent = new Intent(this,VerifyActivity.class);
        intent.putExtra("phone number", phoneNumber);
        intent.putExtra("VerificationID", verificationId);
        startActivity(intent);
    }

    //set login retrofit
    public void loginRetrofit(){
        jsonApi = new UserApi().getJson();
        Call<List<User>> call = jsonApi.setLogin();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                mListusers= response.body();
                Log.d("login", "onResponse: " + response.body().toString());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(ForgotPassActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });


    }
}