package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.apiOps.IGoogleApiInterface;
import com.example.myapplication.model.PlaceDetail;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPlaceActivity extends AppCompatActivity {

    ImageView photo;
    RatingBar ratingBar;
    TextView place_address, opening_hours, place_name;
    Button btn_viewOnMap;

    IGoogleApiInterface mService;
    PlaceDetail mplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);
        mService = Common.getGoogleApiInterface();
        intt();
    }

    private void intt() {
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        place_address =(TextView) findViewById(R.id.place_address);
        place_name =(TextView) findViewById(R.id.place_name);
        opening_hours =(TextView) findViewById(R.id.open_hour);
        btn_viewOnMap  =(Button) findViewById(R.id.show_map);

        // set to empty
        place_name.setText("");
        place_address.setText("");
        opening_hours.setText("");
        btn_viewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mplace.getResult().getUrl()));
                startActivity(mapIntent);

            }
        });

        photo =(ImageView) findViewById(R.id.photo);
        if(Common.currentResults.getPhotos() !=  null && Common.currentResults.getPhotos().length > 0){
            Picasso.with(this)
                    .load(getPhotoOfPlace(Common.currentResults.getPhotos()[0].getPhoto_reference(),1000))
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .error(R.drawable.ic_baseline_error_24)
                    .into(photo);
        }
        if(Common.currentResults.getRating() != null && !TextUtils.isEmpty(Common.currentResults.getRating())){
            ratingBar.setRating(Float.parseFloat(Common.currentResults.getRating()));
        }else{
            ratingBar.setVisibility(View.GONE);
        }

        if(Common.currentResults.getOpening_hours() != null ){
            opening_hours.setText("Open Now:"+ Common.currentResults.getOpening_hours().getOpen_now() );
        }else{
            opening_hours.setVisibility(View.GONE);
        }


        // user service to fetch the address and details
        mService.getDetailPlace(getPlaceDetailUrl(Common.currentResults.getPlace_id()))
                .enqueue(new Callback<PlaceDetail>() {
                    @Override
                    public void onResponse(Call<PlaceDetail> call, Response<PlaceDetail> response) {
                        mplace = response.body();
//                        place_address.setText();

                        System.out.println("Response body"+ response.body());
                        place_address.setText(mplace.getResult().getFormatted_address());
                        place_name.setText(mplace.getResult().getName());

                    }

                    @Override
                    public void onFailure(Call<PlaceDetail> call, Throwable t) {

                    }
                });
    }
    private String getPlaceDetailUrl (String place_id){
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        url.append("?place_id="+place_id);
        url.append("&key="+getResources().getString(R.string.browser_key));
        System.out.println(" String URL place" + url.toString());
        return url.toString();

    }

    private String getPhotoOfPlace (String photo_reference,int maxWidth){
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth=" + maxWidth);
        url.append("&photoreference=" + photo_reference);
        url.append("&key=" + getResources().getString(R.string.browser_key));
        System.out.println(" String URL IMAgee" + url.toString());
        return url.toString();

    }
}