package coupletones.team35.coupletones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

public class AddingLocationActivity extends AppCompatActivity {
    private PlacePicker.IntentBuilder builder;
    private Button pickerBtn;
    private GoogleApiClient mGoogleApiClient;
    private static final int PLACE_PICKER_FLAG = 1;
    private AutoCompleteTextView myLocation;
    private TextView locList;
    private int locCount = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_location);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(AppIndex.API)
                .build();

        setContentView(R.layout.activity_main);

        builder = new PlacePicker.IntentBuilder();
        myLocation = (AutoCompleteTextView) findViewById(R.id.myLocation);
        locList = (TextView) findViewById(R.id.locList);

        pickerBtn = (Button) findViewById(R.id.pickerBtn);
        pickerBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    builder = new PlacePicker.IntentBuilder();
                    Intent intent = builder.build(AddingLocationActivity.this);
                    // Start the Intent by requesting a result, identified by a request code.
                    startActivityForResult(intent, PLACE_PICKER_FLAG);

                }
                catch (GooglePlayServicesRepairableException e)
                {
                    GooglePlayServicesUtil
                            .getErrorDialog(e.getConnectionStatusCode(), AddingLocationActivity.this, 0);
                }
                catch (GooglePlayServicesNotAvailableException e)
                {
                    Toast.makeText(AddingLocationActivity.this, "Google Play Services is not available.",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PLACE_PICKER_FLAG:
                    Place place = PlacePicker.getPlace(data, this);
                    myLocation.setText(place.getName() + ", " + place.getAddress()+ "\nID: " +place.getId());
                    locList.append("\n"+locCount+": "+ place.getId());
                    locCount++;
                    break;
            }
        }
    }

}
