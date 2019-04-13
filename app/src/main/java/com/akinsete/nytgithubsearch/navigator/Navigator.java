package com.akinsete.nytgithubsearch.navigator;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.akinsete.nytgithubsearch.R;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.browser.customtabs.CustomTabsIntent;

/**
 * Created by sundayakinsete on 2019-04-10.
 */

@Singleton
public class Navigator {


    @Inject
    public Navigator(){

    }

    public void openRepoUrl(Context context, String url) {
        if (context != null && !TextUtils.isEmpty(url)) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setToolbarColor(context.getResources().getColor(R.color.colorPrimary));
            builder.setShowTitle(true);
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(url));
        }
    }
}
