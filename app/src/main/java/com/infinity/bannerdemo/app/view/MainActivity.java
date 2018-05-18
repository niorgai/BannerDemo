package com.infinity.bannerdemo.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.infinity.bannerdemo.app.R;
import com.infinity.bannerdemo.app.utils.ImageUtils.ImageItem;
import com.infinity.bannerdemo.app.presenter.ImageAdapter;

import java.util.ArrayList;

import static com.infinity.bannerdemo.app.utils.Constants.IMAGE_ID;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;
    private ImageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGridView();
    }

    private void initGridView() {
        mGridView = findViewById(R.id.grid_view);
        ArrayList<ImageItem> arrayList = new ArrayList<>();
        initArrayList(arrayList);
        mAdapter = new ImageAdapter(MainActivity.this);
        mAdapter.setImageList(arrayList);
        mGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jumpToWallpaperBrowseActivity(mAdapter.getItem(position));
            }
        });
        mGridView.setAdapter(mAdapter);
    }

    private void jumpToWallpaperBrowseActivity(ImageItem imageItem) {
        Intent intent = new Intent(this, ImagePreviewActivity.class);
        intent.putExtra(IMAGE_ID, imageItem.getImageId());
        startActivity(intent);
    }

    private void initArrayList(ArrayList<ImageItem> arrayList) {
        arrayList.add(new ImageItem(R.drawable.image_1));
        arrayList.add(new ImageItem(R.drawable.image_2));
        arrayList.add(new ImageItem(R.drawable.image_3));
        arrayList.add(new ImageItem(R.drawable.image_4));
        arrayList.add(new ImageItem(R.drawable.image_5));
    }
}
