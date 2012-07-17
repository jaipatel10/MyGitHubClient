
package com.mygithubclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.EventService;
import org.eclipse.egit.github.core.service.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_EventsPage extends Activity {

    private ImageView img_next, img_back;

    private TextView txt_nameofuser, txt_reponame, txt_repodate, txt_pagenum;

    private ProgressDialog pdg;

    private String result = "";

    private LazyAdapter adapter;

    int j = 1;

    int nextPage = 0;

    int cnt = 1;

    Typeface font;

    EventService event;

    ListView mycutomlist;

    public static ArrayList<String> myarrlist = new ArrayList<String>();

    ArrayList<String> myimages = new ArrayList<String>();

    ArrayList<String> mynames = new ArrayList<String>();

    ArrayList<String> repopath = new ArrayList<String>();

    ArrayList<String> repodate = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_eventspage);

        // --------------------Getting ID's of all the elements----------------

        img_back = (ImageView)findViewById(R.id.img_back);
        img_next = (ImageView)findViewById(R.id.img_next);
        mycutomlist = (ListView)findViewById(R.id.lst_eventlist);
        txt_nameofuser = (TextView)findViewById(R.id.txt_nameofuserevent);
        txt_reponame = (TextView)findViewById(R.id.txt_reponame);
        txt_repodate = (TextView)findViewById(R.id.txt_repodate);
        txt_pagenum = (TextView)findViewById(R.id.txt_pagenum);

        font = Typeface.createFromAsset(getAssets(), "GeosansLight.ttf");

        txt_pagenum.setText("Page No. 1");

        // -----------------Event Service Object-------------------------------

        event = new EventService(Activity_LoginPage.client);

        // -----------------------Load Next Data------------------------------

        new LoadNextData().execute();

        // ---------------------------Next Button Click
        // Event--------------------

        img_next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                myimages.clear();
                mynames.clear();
                repopath.clear();
                repodate.clear();
                mycutomlist.setAdapter(null);
                new LoadNextData().execute();

            }
        });

        // --------------------Back Button Click
        // Event----------------------------

        img_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (nextPage == 1) {

                    Toast.makeText(Activity_EventsPage.this, "You are not able to go back!!!",
                            Toast.LENGTH_SHORT).show();

                } else {

                    myimages.clear();
                    mynames.clear();
                    repopath.clear();
                    repodate.clear();
                    mycutomlist.setAdapter(null);
                    new LoadBackData().execute();

                }
            }
        });

    }

    public class LoadNextData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            try {

                pdg = ProgressDialog.show(Activity_EventsPage.this, "", "Loading data...", true);

            } catch (Exception exp) {

                System.out.println("Inside progreess: " + exp.getMessage());
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub

            nextPage++;

            PageIterator<Event> mylist = event.pagePublicEvents();

            try {

                while (mylist.hasNext()) {

                    Collection<Event> mypage = mylist.iterator().next();

                    if (cnt == nextPage) {

                        Iterator<Event> ltr = mypage.iterator();

                        while (ltr.hasNext()) {

                            Event event = ltr.next();

                            try {

                                myimages.add(event.getActor().getAvatarUrl());
                                mynames.add(event.getRepo().getName());
                                repopath.add(event.getRepo().getUrl());
                                repodate.add("Created At : " + event.getCreatedAt());

                                /* mynames.add(ltr.next().getActor().getName()); */

                                // System.out.println("opq "+ltr.next().getActor().getAvatarUrl());
                            } catch (Exception exp) {
                                System.out.println("Exception :" + exp.getMessage());
                            }
                        }

                        break;
                    }

                }
                cnt++;

            } catch (Exception exp) {

                result = "complete";

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void res) {
            // TODO Auto-generated method stub

            if (result.equalsIgnoreCase("complete")) {

                try {

                    pdg.dismiss();
                    pdg = null;
                } catch (Exception exp) {

                }

                Toast.makeText(Activity_EventsPage.this, "No more data found!!!",
                        Toast.LENGTH_SHORT).show();

            } else {

                txt_pagenum.setText("Page No. " + nextPage);
                adapter = new LazyAdapter(Activity_EventsPage.this, myimages, mynames, repopath,
                        repodate, font);
                mycutomlist.setAdapter(adapter);

                try {

                    pdg.dismiss();
                    pdg = null;
                } catch (Exception exp) {

                }

            }

        }

    }

    public class LoadBackData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {

            try {

                pdg = ProgressDialog.show(Activity_EventsPage.this, "", "Loading data...", true);

            } catch (Exception exp) {

                System.out.println("Inside progreess: " + exp.getMessage());
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub

            nextPage--;

            PageIterator<Event> mylist = event.pagePublicEvents();

            while (mylist.hasNext()) {

                cnt--;

                Collection<Event> mypage = mylist.iterator().next();

                if (cnt == nextPage) {

                    Iterator<Event> ltr = mypage.iterator();

                    while (ltr.hasNext()) {

                        Event event = ltr.next();

                        try {

                            myimages.add(event.getActor().getAvatarUrl());
                            mynames.add(event.getRepo().getName());
                            repopath.add(event.getRepo().getUrl());
                            repodate.add("Created At : " + event.getCreatedAt());

                            // System.out.println("opq "+ltr.next().getActor().getAvatarUrl());
                        } catch (Exception exp) {
                            System.out.println("Exception :" + exp.getMessage());
                        }
                    }

                    break;
                }

            }

            cnt++;

            return null;
        }

        @Override
        protected void onPostExecute(Void res) {
            // TODO Auto-generated method stub

            txt_pagenum.setText("Page No. " + nextPage);
            adapter = new LazyAdapter(Activity_EventsPage.this, myimages, mynames, repopath,
                    repodate, font);
            mycutomlist.setAdapter(adapter);

            try {

                pdg.dismiss();
                pdg = null;
            } catch (Exception exp) {

            }

        }

    }

    /*
     * EventService event = new EventService(Activity_LoginPage.client);
     * //Toast.makeText(Activity_EventsPage.this, "value :"+
     * event.pagePublicEvents().getRequest().PAGE_FIRST,
     * Toast.LENGTH_SHORT).show(); while(mylist.hasNext()){
     * for(Collection<Event> myevents : mylist){ System.out.println("afda");
     * while(myevents.iterator().hasNext()){ System.out.println("urls: " +
     * myevents.iterator().next().getActor().getAvatarUrl()); } }
     * Collection<Event> mypage = mylist.iterator().next(); Iterator<Event> ltr
     * = mypage.iterator(); while(ltr.hasNext()){
     * System.out.println("opq "+ltr.next().getActor().getId()); } break; }
     * Object [] obj = myevent.toArray(); for(int j = 0 ; j< obj.length ; j++){
     * System.out.println(myevent.iterator().next().getActor().getAvatarUrl());
     * } }
     */
}
