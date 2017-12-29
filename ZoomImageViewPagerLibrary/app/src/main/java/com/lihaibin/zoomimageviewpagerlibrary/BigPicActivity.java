package com.lihaibin.zoomimageviewpagerlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.lihaibin.zoomimageviewpagerlibrary.largeimage.LargeImageView;

import java.util.ArrayList;

/**
 * 创建者     李海镔
 * 创建时间   2017/12/21 10:27
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class BigPicActivity extends Activity {
    private ViewPager mVpPictureContet;
    private ArrayList mImgs;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigpic);
        init();
        loadData();
    }


    protected void init() {
        mVpPictureContet = (ViewPager) findViewById(R.id.vp_picture_contet);
        mImgs = getIntent().getStringArrayListExtra("imgs");
        position = getIntent().getIntExtra("position",0);

    }
    protected void loadData() {
        BasePagerAdapter adapter =new BasePagerAdapter(this,mImgs);
        mVpPictureContet.setAdapter(adapter);
        mVpPictureContet.setOffscreenPageLimit(2);
        mVpPictureContet.setCurrentItem(position);
    }
    class BasePagerAdapter extends PagerAdapter {
        private LargeImageView mScaleViews [];
        private Activity mContext;
        private ArrayList imgs;
        public BasePagerAdapter(Activity context, ArrayList imags) {
            mContext=context;
            this.imgs=imags;
            mScaleViews=new LargeImageView[imgs.size()];
        }

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView(mScaleViews[position]);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final LargeImageView mImageView = new LargeImageView(mContext);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.finish();
                }
            });
            Glide.with(mContext).load(imgs.get(position)).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    mImageView.setImage(resource);
                }
            });
            mScaleViews[position] = mImageView;
            container.addView(mImageView);
            return mImageView;
        }

    }
    public static void start(Context context, ArrayList<String> imgs, int position){
        Intent intent = new Intent(context,BigPicActivity.class);
        if(imgs==null){
            Toast.makeText(context, "ArrayList<String> imgs is null!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(imgs.size()==0){
            Toast.makeText(context, "ArrayList<String> imgs size() is 0", Toast.LENGTH_SHORT).show();
            return;
        }
        if(position<0|position>imgs.size()){
            Toast.makeText(context, "value of position should be 0-"+imgs.size(), Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtra("imgs",imgs);
        intent.putExtra("position",position);
        context.startActivity(intent);
    }
}
