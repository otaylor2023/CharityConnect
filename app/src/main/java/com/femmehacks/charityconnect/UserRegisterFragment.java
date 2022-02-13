package com.femmehacks.charityconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.femmehacks.charityconnect.databinding.FragmentLoginBinding;
import com.femmehacks.charityconnect.databinding.FragmentUserRegisterBinding;
import com.femmehacks.charityconnect.storage.OnLoginCheckedCallback;
import com.femmehacks.charityconnect.storage.ServerStorage;
import com.femmehacks.charityconnect.storage.UserPOJO;

import java.security.NoSuchAlgorithmException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "fh.register";
    private FragmentUserRegisterBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserRegisterFragment newInstance(String param1, String param2) {
        UserRegisterFragment fragment = new UserRegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG,"created");
        binding = FragmentUserRegisterBinding.inflate(inflater, container, false);


        binding.signupButton.findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "clicked");
                String email = ((EditText)getView().findViewById(R.id.signup_email)).getText().toString();
                String password = ((EditText)getView().findViewById(R.id.signup_password)).getText().toString();
                String name = ((EditText)getView().findViewById(R.id.signup_name)).getText().toString();
                UserPOJO userPOJO = new UserPOJO();
                userPOJO.setEmail(email);
                userPOJO.setDisplayName(name);
                try {
                    userPOJO.setHashedPassword(UserPOJO.encodeSHA256(password));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                ServerStorage.addUser(userPOJO);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
        return binding.getRoot();
    }
}