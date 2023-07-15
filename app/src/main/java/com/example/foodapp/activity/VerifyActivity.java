package com.example.foodapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.foodapp.databinding.ActivityVerifyBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {

    public static final String TAG = VerifyActivity.class.getName();

    private ActivityVerifyBinding binding;
    private String mPhoneNumber;
    private String verifiId;

    private FirebaseAuth mAuth;

    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        getDataOtp();
        // sự kiện
        event ();

    }

    private void event() {
        binding.btnVeriry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp1 = binding.edtInputPhone1.getText().toString().trim();
                String otp2 = binding.edtInputPhone2.getText().toString().trim();
                String otp3 = binding.edtInputPhone3.getText().toString().trim();
                String otp4 = binding.edtInputPhone4.getText().toString().trim();
                String otp5 = binding.edtInputPhone5.getText().toString().trim();
                String otp6 = binding.edtInputPhone6.getText().toString().trim();
                String otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;
                onClickSendOtp(otp);
            }
        });
        binding.imgBackVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.tvResendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickOtpAgain();
            }
        });
    }
    /**   GET DATA OTP INTENT FROM FORGOT PASSWORD  **/
    private void getDataOtp(){
        mPhoneNumber = getIntent().getStringExtra("phone number");
        verifiId     = getIntent().getStringExtra("VerificationID");
    }
    /**  CLICK OTP AGAIN CONFIRM             **/
    private void onClickOtpAgain() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mPhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)
                        .setForceResendingToken(mForceResendingToken)
                        // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(VerifyActivity.this, "Falled", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);

                                verifiId = verificationId;
                                mForceResendingToken = forceResendingToken;


                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    /**         CLICK OTP CONFIRM                   **/
    private void onClickSendOtp(String otp){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifiId,otp);
        signInWithPhoneAuthCredential(credential);
    }

    /** **/
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
                            gotoResetPass(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(VerifyActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private void gotoResetPass(String phoneNumber) {
        Intent intent = new Intent(this, ResetPassword.class);
        intent.putExtra("Phone number", phoneNumber);
        startActivity(intent);
    }
}