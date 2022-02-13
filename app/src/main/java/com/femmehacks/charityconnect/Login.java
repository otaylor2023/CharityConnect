package com.femmehacks.charityconnect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import com.femmehacks.charityconnect.databinding.FragmentLoginBinding;
import com.femmehacks.charityconnect.storage.OnLoginCheckedCallback;
import com.femmehacks.charityconnect.storage.ServerStorage;
import com.femmehacks.charityconnect.storage.UserPOJO;

public class Login extends Fragment {

    private FragmentLoginBinding binding;
    private final String TAG = "fh.login";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.d(TAG, "created");
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);


        binding.logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "clicked");
                String email = ((EditText)getView().findViewById(R.id.login_user_email)).getText().toString();
                String password = ((EditText)getView().findViewById(R.id.login_user_password)).getText().toString();
                ServerStorage.attemptSignIn(email, password, new OnLoginCheckedCallback() {
                    @Override
                    public void onLoginChecked(UserPOJO match, boolean isOrg) {
                        if (match != null) {
                            Log.d(TAG, "success");
                            if (!isOrg) {
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.putExtra("user", match);
                                startActivity(intent);
                            }

                        }
                        else {
                            ServerStorage.attemptOrgSignIn(email, password, new OnLoginCheckedCallback() {
                                @Override
                                public void onLoginChecked(UserPOJO match, boolean isOrg) {
                                    if (match != null) {
                                        Log.d(TAG, "success");
                                        Intent intent = new Intent(getActivity(), OrganizationAddActvity.class);
                                        intent.putExtra("user", match);
                                        startActivity(intent);

                                    }
                                    else {
                                        Log.d(TAG, "invalid pass");
                                        getView().findViewById(R.id.invalid_pass).setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        }
                    }
                });







            }
        });


        binding.signUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                NavHostFragment.findNavController(Login.this)
                        .navigate(R.id.action_login_to_userRegisterFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}