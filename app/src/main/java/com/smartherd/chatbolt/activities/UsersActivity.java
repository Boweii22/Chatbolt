package com.smartherd.chatbolt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.smartherd.chatbolt.R;
import com.smartherd.chatbolt.adapters.UsersAdapter;
import com.smartherd.chatbolt.databinding.ActivityUsersBinding;
import com.smartherd.chatbolt.listeners.UserListener;
import com.smartherd.chatbolt.models.User;
import com.smartherd.chatbolt.utilites.Constants;
import com.smartherd.chatbolt.utilites.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity implements UserListener {

//    private ActivityUsersBinding binding;
    private PreferenceManager preferenceManager;
    private AppCompatImageView imageBack;
    private TextView textErrorMessage;
    private RecyclerView usersRecyclerView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_users);
        preferenceManager = new PreferenceManager(getApplicationContext());
        imageBack = findViewById(R.id.imageBack);
        textErrorMessage = findViewById(R.id.textErrorMessage);
        usersRecyclerView = findViewById(R.id.usersRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        getUsers();
        setListeners();
    }

    private void setListeners(){
//        binding.imageBack.setOnClickListener(v -> onBackPressed());
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void showErrorMessage(){
        textErrorMessage.setText(String.format("%s","No user available"));
        textErrorMessage.setVisibility(View.VISIBLE);
    }

    private void getUsers(){
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    String currentUserId = preferenceManager.getString(Constants.KEY_USER_ID);
                    if (task.isSuccessful() && task.getResult() != null){
                        List<User> users = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                            if(currentUserId.equals(queryDocumentSnapshot.getId())){
                                continue;
                            }
                            User user = new User();
                            user.name = queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            user.email = queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            user.image = queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            user.token = queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                            user.id = queryDocumentSnapshot.getId();
                            users.add(user);
                        }
                        if(users.size() > 0){
                            UsersAdapter usersAdapter = new UsersAdapter(users, this);
                            usersRecyclerView.setAdapter(usersAdapter);
                            usersRecyclerView.setVisibility(View.VISIBLE);
                        }else {
                            showErrorMessage();
                        }
                    }else {
                        showErrorMessage();
                    }
                });
    }

    private void loading(Boolean isLoading){
        if (isLoading){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onUserClicked(User user) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Constants.KEY_USER, user);
        startActivity(intent);
        finish();
    }
}