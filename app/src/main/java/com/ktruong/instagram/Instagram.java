package com.ktruong.instagram;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * CLIENT ID	3f9fbd99bf0d4aa481d94c9283403952
 */
public class Instagram extends ActionBarActivity {

    private static final Logger logger = Logger.getLogger(Instagram.class.getName());

    private static final String CLIENT_ID = "3f9fbd99bf0d4aa481d94c9283403952";
    private List<InstagramPhoto> photos;
    private InstagramPhotoAdapter instagramPhotoAdapter;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        photos = new ArrayList<>();
        instagramPhotoAdapter = new InstagramPhotoAdapter(this, photos);

        ListView photoListView = (ListView) findViewById(R.id.photoListView);
        photoListView.setAdapter(instagramPhotoAdapter);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchPopularPhotos();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        fetchPopularPhotos();
    }

    private void fetchStuffPhotos() {
        for (int i = 0; i < 5; i++) {
            InstagramPhoto instagramPhoto = new InstagramPhoto();
            instagramPhoto.setCaptionFromUserName("caption-user" + i);
            instagramPhoto.setCaption("some long caption text " + i);
            instagramPhoto.setUserFullName("my long name " + i);
            instagramPhoto.setName("user" + i);
            instagramPhoto.setLikesCount(2000);


            photos.add(instagramPhoto);
        }
        instagramPhotoAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instagram, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 02-18 18:10:47.996    2489-2489/com.ktruong.instagram I/DEBUG﹕ {"meta":{"code":200},"data":[{"attribution":null,"tags":[],"location":{"latitude":34.072545,"name":"Dodger Stadium","longitude":-118.243831,"id":2669213},"comments":{"count":232,"data":[{"created_time":"1424311500","text":"@lovey3784 AHHHHHHHHHH!!!!!!","from":{"username":"pnickolas1","profile_picture":"https:\/\/instagramimages-a.akamaihd.net\/profiles\/profile_180139691_75sq_1358111896.jpg","id":"180139691","full_name":"Peter Nickolas"},"id":"923514260375225290"},{"created_time":"1424311542","text":"@pnickolas1 ahhh!!! Holy geez!!!!!","from":{"username":"lovey3784","profile_picture":"https:\/\/igcdn-photos-g-a.akamaihd.net\/hphotos-ak-xap1\/t51.2885-19\/10808847_795379213857542_1103464301_a.jpg","id":"143581556","full_name":"Jamie"},"id":"923514614919742440"},{"created_time":"1424311578","text":"@alyssamylene_","from":{"username":"a_eri_","profile_picture":"https:\/\/igcdn-photos-b-a.akamaihd.net\/hphotos-ak-xfa1\/t51.2885-19\/10899155_551399831629113_1175319192_a.jpg","id":"144349231","full_name":"a_eri_"},"id":"923514915777167368"},{"created_time":"1424311650","text":"Yess","from":{"username":"gi_no_1","profile_picture":"https:\/\/igcdn-photos-g-a.akamaihd.net\/hphotos-ak-xaf1\/t51.2885-19\/10950376_768402236577046_1112978759_a.jpg","id":"54346809","full_name":"Gino C"},"id":"923515520696467522"},{"created_time":"1424311704","text":"@let1c1a9 ⚾️","from":{"username":"ricardizo","profile_picture":"https:\/\/igcdn-photos-b-a.akamaihd.net\/hphotos-ak-xpa1\/t51.2885-19\/928823_403964636418569_1794071799_a.jpg","id":"53429386","full_name":"Ricardo V®"},"id":"923515973681299570"},{"created_time":"1424311824","text":"@ray_xamp prepare your heart....","from":{"username":"danapacheco","profile_picture":"https:\/\/instagramimages-a.akamaihd.net\/profiles\/profile_7948315_75sq_1369113796.jpg","id":"7948315","full_name":"danapacheco"},"id":"923516981606105305"},{"created_time":"1424311824","text":"@steveeazy91 I just got goosebumps seeing this pic","from":{"username":"maripixxie","profile_picture":"https:\/\/igcdn-photos-a-a.akamaihd.net\/hphotos-ak-xpa1\/t51.2885-19\/10513792_1448081755447800_457754767_a.jpg","id":"17118116","full_name":"Miss Mari✌️"},"id":"923516979987103960"},{"created_time":"1424311826","text":"Hi","from":{"username":"mlbdebate","profile_picture":"https:\/\/igcdn-photos-a-a.akamaihd.net\/hphotos-ak-xfa1\/t51.2885-19\/11008199_1438028086488480_1508511213_a.jpg","id":"1711991944","full_name":"Mlb"},"id":"923516993350156506"}]},"filter":"Normal","created_time":"1424307902","link":"http:\/\/instagram.com\/p\/zQ3-BprEJL\/","likes":{"count":21005,"data":[{"username":"kathayyygarcia","profile_picture":"https:\/\/igcdn-photos-e-a.akamaihd.net\/hphotos-ak-xaf1\/t51.2885-19\/10979524_515448828595620_1758581805_a.jpg","id":"313091304","full_name":"kathayyygarcia"},{"username":"rojass4","profile_picture":"https:\/\/igcdn-photos-d-a.akamaihd.net\/hphotos-ak-xfa1\/t51.2885-19\/10914596_1785357265022723_998342560_a.jpg","id":"29217465","full_name":"Yasmin Rojas 🍂"},{"username":"princessivii04_","profile_picture":"https:\/\/igcdn-photos-f-a.akamaihd.net\/hphotos-ak-xfa1\/t51.2885-19\/10844066_797518596964149_4541475_a.jpg","id":"1370910258","full_name":"👑😘 Īvíåňñåhh Śķý😘👑"},{"username":"gatorbootz","profile_picture":"https:\/\/igcdn-photos-a-a.akamaihd.net\/hphotos-ak-xap1\/t51.2885-19\/1168829_1508566916031704_1088457995_a.jpg","id":"246380927","full_name":"Juan Aguilera"}]},"images":{"low_resolution":{"url":"http:\/\/scontent-b.cdninstagram.com\/hphotos-xfa1\/t51.2885-15\/s306x306\/e15\/11017610_649815841807805_863875097_n.jpg","width":306,"height":306},"thumbnail":{"url":"http:\/\/scontent-b.cdninstagram.com\/hphotos-xfa1\/t51.2885-15\/s150x150\/e15\/11017610_649815841807805_863875097_n.jpg","width":150,"height":150},"standard_resolution":{"url":"http:\/\/scontent-b.cdninstagram.com\/hphotos-xfa1\/t51.2885-15\/e15\/11017610_649815841807805_863875097_n.jpg","width":640,"height":640}},"users_in_photo":[],"caption":{"created_time":"1424307902","text":"Hurr
     */
    public void fetchPopularPhotos() {
        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        Log.i("INFO", "url " + url);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("DEBUG", response.toString());
                // Remember to CLEAR OUT old items before appending in the new ones
                instagramPhotoAdapter.clear();
                try {
                    JSONArray dataArray = response.getJSONArray("data");
                    int size = (dataArray != null) ? dataArray.length() : 0;
                    //02-18 20:21:13.255    2329-2329/com.ktruong.instagram I/INFO﹕ row {"attribution":null,"tags":["getlaqued","laque","laquenailbar"],"location":{"latitude":34.179577975,"longitude":-118.40618637},"comments":{"count":29,"data":[{"created_time":"1424317169","text":"@king_da_shooter22","from":{"username":"060.bando","profile_picture":"https:\/\/igcdn-photos-f-a.akamaihd.net\/hphotos-ak-xfa1\/t51.2885-19\/10832219_1571548766418029_996659391_a.jpg","id":"397261966","full_name":"Call Me NASA🚀"},"id":"923561816847040138"},{"created_time":"1424317755","text":"@dalqertas","from":{"username":"dalzaid","profile_picture":"https:\/\/igcdn-photos-f-a.akamaihd.net\/hphotos-ak-xaf1\/t51.2885-19\/11008266_595115873924421_277496652_a.jpg","id":"34620808","full_name":"danah"},"id":"923566726808353871"},{"created_time":"1424318025","text":"@glitterdreams I want to do this","from":{"username":"karinbobarin","profile_picture":"https:\/\/scontent-a.cdninstagram.com\/hphotos-xaf1\/t51.2885-19\/10914610_775316482559359_94923048_a.jpg","id":"17746834","full_name":"Karin Maltrud"},"id":"923568994534309120"},{"created_time":"1424318194","text":"I love my thick eyebrows it's the beauty of being middle eastern were blessed 🙌🙌 Ty again @laquenailbar for doing an amazing job your amazing marina 😘😘","from":{"username":"connaga","profile_picture":"https:\/\/igcdn-photos-c-a.akamaihd.net\/hphotos-ak-xfa1\/t51.2885-19\/10954700_1542272332688210_216872594_a.jpg","id":"18725500","full_name":"💜 Con Con 💜"},"id":"923570414130052461"},{"created_time":"1424318521","text":"@laquenailbar وين الفرق بالموضوع","from":{"username":"dr.sarah_alaa","profile_picture":"https:\/\/igcdn-photos-h-a.akamaihd.net\/hphotos-ak-xaf1\/t51.2885-19\/10985976_301619716678207_2016950269_a.jpg","id":"1698266990","full_name":"dr.sarah_alaa"},"id":"923573159075528271"},{"created_time":"1424318797","text":"@laquenailbar That looks like @mimigstyle eyebrows ... Great Job 💯✔️","from":{"username":"juicingchangedmylife","profile_picture":"https:\/\/igcdn-photos-e-a.akamaihd.net\/hphotos-ak-xfa1\/t51.2885-19\/10990634_1548067552112164_1599346332_a.jpg","id":"194590796","full_name":"JuicingChangedMyLife💚⚫💛"},"id":"923575475077922576"},{"created_time":"1424319130","text":"@kingmarco20","from":{"username":"priscilla_mz6","profile_picture":"https:\/\/igcdn-photos-g-a.akamaihd.net\/hphotos-ak-xaf1\/t51.2885-19\/10949082_1541243236147622_97025743_a.jpg","id":"319938045","full_name":"Priscilla💅"},"id":"923578265732922392"},{"created_time":"1424319668","text":"this is amazing!","from":{"username":"missfabulousmakeup","profile_picture":"https:\/\/igcdn-photos-g-a.akamaihd.net\/hphotos-ak-xaf1\/t51.2885-19\/10932433_754669384617590_1188584089_a.jpg","id":"968837323","full_name":"✨🎀Alyssa Chambers🎀✨"},"id":"923582778971798856"}]},"filter":"Valencia","created_time":"1424315921","link":"http:\/\/instagram.com\/p\/zRHQ2ayD62\/","likes":{"count":5657,"data":[{"username":"mercycosita","profile_picture":"https:\/\/instagramimages-a.akamaihd.net\/profiles\/profile_175524572_75sq_1398751785.jpg","id":"175524572","full_name":"mercy Paz💋"},{"username":"mamagon68","profile_picture":"https:\/\/instagramimages-a.akamaihd.net\/profiles\/profile_412395147_75sq_1375673134.jpg","id":"412395147","full_name":"Jack"},{"username":"mero_elanany","profile_picture":"https:\/\/igcdn-photos-d-a.akamaihd.net\/hphotos-ak-xfa1\/t51.2885-19\/10950533_862545263766907_237144805_a.jpg","id":"1409609980","full_name":"mero_elanany"},{"username":"johnna_na_na","profile_picture":"https:\/\/igcdn-photos-d-a.akamaihd.net\/hphotos-ak-xap1\/t51.2885-19\/10809624_1501908630094763_82943286_a.jpg","id":"1073953005","full_name":"johnna_na_na"}]},"images":{"low_resolution":{"url":"http:\/\/scontent-a.cdninstagram.com\/hphotos-xaf1\/t51.2885-15\/s306x306\/e15\/11007753_1631564573733027_1852255249_n.jpg","width":306,"height":306},"thumbnail":{"url":"http:\/\/scontent-a.cdninstagram.com\/hphotos-xaf1\/t51.2885-15\/s150x150\/e15\/11007753_1631564573733027_1852255249_n.jpg","width":150,"height":150
                    for (int i = 0; i < size; i++) {
                        JSONObject photoJson = dataArray.getJSONObject(i);
                        JSONObject userJson = photoJson.getJSONObject("user");

                        InstagramPhoto instagramPhoto = new InstagramPhoto();
                        instagramPhoto.setName(userJson.getString("username"));
                        instagramPhoto.setUserFullName(userJson.getString("full_name"));
                        String profileImgUrl = userJson.getString("profile_picture");
                        if (profileImgUrl != null) {
                            instagramPhoto.setProfileImageUrl(Uri.parse(profileImgUrl));
                        }

                        JSONObject commentsJson = photoJson.getJSONObject("likes");
                        if (commentsJson != null) {
                            instagramPhoto.setLikesCount(commentsJson.getInt("count"));
                        }

                        JSONObject imagesJson = photoJson.getJSONObject("images");
                        String standardImgUrl = imagesJson.getJSONObject("standard_resolution").getString("url");
                        instagramPhoto.setImageUrl(Uri.parse(standardImgUrl));

                        long createLongSec = photoJson.getLong("created_time");
                        CharSequence createdTimeStr = DateUtils.getRelativeTimeSpanString(createLongSec * 1000, System.currentTimeMillis(), DateUtils.HOUR_IN_MILLIS);
                        instagramPhoto.setCreatedTime(formatNumbers(createdTimeStr.toString()));

                        if (!photoJson.isNull("caption")) {
                            JSONObject captionJson = photoJson.getJSONObject("caption");
                            instagramPhoto.setCaption(captionJson.getString("text"));
                            instagramPhoto.setCaptionFromUserName(captionJson.getJSONObject("from").getString("username"));
                        }

                        photos.add(instagramPhoto);
                    }
                } catch (JSONException e) {
                    for (int i = 0; i < 5; i++) {
                        InstagramPhoto instagramPhoto = new InstagramPhoto();
                        instagramPhoto.setCaptionFromUserName("caption-user" + i);
                        instagramPhoto.setCaption("some long caption text " + i);
                        instagramPhoto.setUserFullName("my long name " + i);
                        instagramPhoto.setName("user" + i);


                        photos.add(instagramPhoto);
                    }
                    e.printStackTrace();
                    Log.e("ERROR", "failed to load data", e);
                }

                swipeContainer.setRefreshing(false);
                instagramPhotoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                for (int i = 0; i < 5; i++) {
                    InstagramPhoto instagramPhoto = new InstagramPhoto();
                    instagramPhoto.setCaptionFromUserName("caption-user" + i);
                    instagramPhoto.setCaption("some long caption text " + i);
                    instagramPhoto.setUserFullName("my long name " + i);
                    instagramPhoto.setName("user" + i);


                    photos.add(instagramPhoto);
                }
                instagramPhotoAdapter.notifyDataSetChanged();
            }
        });
    }

    private String formatNumbers(String input) {
        String[] hourTokenizer = input.split(" ");
        return hourTokenizer[0] + hourTokenizer[1].charAt(0);
    }
}
