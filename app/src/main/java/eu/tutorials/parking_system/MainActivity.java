package eu.tutorials.parking_system;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {
    private TextView box1, box2, box3;
    private DatabaseReference mDatabaseSensor1, mDatabaseSensor2, mDatabaseSensor3;
    FirebaseAuth auth;
    FirebaseUser user;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseSensor1 = FirebaseDatabase.getInstance().getReference("sensor_data/sensor_1");
        mDatabaseSensor2 = FirebaseDatabase.getInstance().getReference("sensor_data/sensor_2");
        mDatabaseSensor3 = FirebaseDatabase.getInstance().getReference("sensor_data/sensor_3");

        box1 = findViewById(R.id.box1);
        box2 = findViewById(R.id.box2);
        box3 = findViewById(R.id.box3);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        btn = findViewById(R.id.btn_logout);

        if (user == null) {
            Intent intent = new Intent(MainActivity.this, Login_Page.class);
            startActivity(intent);
            finish();
        } else {
            fetchSensorData();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateLogoutButton();
            }
        });
    }

    private void fetchSensorData() {
        mDatabaseSensor1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer sensorValue = dataSnapshot.child("value").getValue(Integer.class);
                updateBox(box1, sensorValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        mDatabaseSensor2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer sensorValue = dataSnapshot.child("value").getValue(Integer.class);
                updateBox(box2, sensorValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        mDatabaseSensor3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer sensorValue = dataSnapshot.child("value").getValue(Integer.class);
                updateBox(box3, sensorValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void updateBox(TextView boxToUpdate, Integer sensorValue) {
        if (sensorValue == null) {
            boxToUpdate.setText("Unknown");
            boxToUpdate.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));  // Gray color for unknown
        } else {
            int color = (sensorValue == 0) ?
                    ContextCompat.getColor(this, android.R.color.holo_red_light) :  // Red for occupied
                    ContextCompat.getColor(this, android.R.color.holo_green_light);  // Green for unoccupied

            // Animate the background color change
            animateColorChange(boxToUpdate, color);

            boxToUpdate.setText(sensorValue == 0 ? "Occupied" : "Unoccupied");
        }
    }

    private void animateColorChange(View view, int toColor) {
        ObjectAnimator colorAnim = ObjectAnimator.ofArgb(view, "backgroundColor", ((TextView) view).getCurrentTextColor(), toColor);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.1f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.1f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(colorAnim, scaleX, scaleY);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    private void animateLogoutButton() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(btn, "scaleX", 1f, 0.9f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(btn, "scaleY", 1f, 0.9f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(btn, "alpha", 1f, 0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, alpha);
        animatorSet.setDuration(300);
        animatorSet.start();

        // After animation ends, navigate to login page
        animatorSet.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, Login_Page.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (auth != null) {
            auth.removeAuthStateListener(this);
        }
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

    }
}
