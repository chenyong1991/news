package com.example.news.activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.news.App;
import com.example.news.R;
import com.example.news.fragmnet.ChatFragmnet;
import com.example.news.fragmnet.ImageFragmnet;
import com.example.news.fragmnet.LikeFragmnet;
import com.example.news.fragmnet.NewsFragmnet;
import com.example.news.utils.Constant;
import com.wilddog.client.DataSnapshot;
import com.wilddog.client.SyncError;
import com.wilddog.client.SyncReference;
import com.wilddog.client.ValueEventListener;
import com.wilddog.wilddogauth.model.WilddogUser;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar_activity_main)
    Toolbar toolbarActivityMain;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;
    @BindView(R.id.fl_content_activity_main)
    FrameLayout flContentActivityMain;
    @BindView(R.id.navigetion_activity_main)
    NavigationView navigetionActivityMain;

    private CircleImageView circleImageView;

    private FragmentManager fragmentManager;

    private List<Fragment> fragmentList;

    private Fragment currFragment;//current


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化toolbar
        initToolbar();
        //初始化片段管理者
        initFragments();
        //初始化导航视图
        initNavgetionview();
        //初始化野狗
        initWilddog();
    }

    /**
     * 初始化wilddong
     */
    private void initWilddog() {
        WilddogUser user = App.user;//user = null;

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (App.user != null) {

                    //显示更换头像对话框
                    showDialog();

                } else {
                    //如果没有登录用户则跳转到登录注册页面
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    startActivityForResult(intent, Constant.GET_IMAGE_FROM_SERVICE);

                }

            }
        });

        if (user != null) {

            final String uid = user.getUid();
            //野狗的的value改变监听事件
            ValueEventListener postListener = new ValueEventListener() {
                //当value改变时调用该方法
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String imgStr = (String) dataSnapshot.getValue();

                    //把string转化成bitmap
                    byte[] decode = Base64.decode(imgStr, Base64.DEFAULT);

                    Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);

                    circleImageView.setImageBitmap(bitmap);

                }

                @Override
                public void onCancelled(SyncError syncError) {
                    // 获取数据失败，打印错误信息。
                }
            };
            //给当前数据库设置数据改变监听器
            App.ref.child(App.user.getUid()).addValueEventListener(postListener);
        }
    }

    /**
     * 初始化导航视图
     */
    private void initNavgetionview() {

        circleImageView = (CircleImageView) navigetionActivityMain.getHeaderView(0).findViewById(R.id.profile_image);
        //默认切换第一个fragment
        navigetionActivityMain.setCheckedItem(R.id.new_navgation);
        currFragment = fragmentList.get(0);
        fragmentManager.beginTransaction().add(R.id.fl_content_activity_main, currFragment).commit();
        //切换toggle,使toolbar和侧拉菜单关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, activityMain, toolbarActivityMain, R.string.open, R.string.close);
        toggle.syncState();
        //侧拉菜单的点击事件
        initNavgetionviewListener();
    }

    /**
     * 初始化导航视图的监听事件
     */
    private void initNavgetionviewListener() {
        navigetionActivityMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int index = 0;

                switch (item.getItemId()) {

                    case R.id.new_navgation:
                        index = 0;
                        toolbarActivityMain.setTitle("新闻");
                        break;
                    case R.id.image_navgation:
                        index = 1;
                        toolbarActivityMain.setTitle("图片");
                        break;
                    case R.id.like_navgation:
                        index = 2;
                        toolbarActivityMain.setTitle("收藏");
                        break;
                    case R.id.chat_navgation:
                        index = 3;
                        toolbarActivityMain.setTitle("聊天");
                        break;

                }
                //切换片段
                replaceFragment(index);

                return true;
            }
        });
    }

    /**
     * 切换片段
     * @param index
     */
    private void replaceFragment(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //切换fragment

        Fragment nextFragment = fragmentList.get(index);

        if (nextFragment != currFragment) {

            if (!nextFragment.isAdded()) {

                if (currFragment != null) {

                    transaction.hide(currFragment);

                }
                transaction.add(R.id.fl_content_activity_main, nextFragment);

            } else {

                if (currFragment != null) {

                    transaction.hide(currFragment);

                }

                transaction.show(nextFragment);

            }

            currFragment = nextFragment;

        }

        //transaction.replace(R.id.fl_content_activity_main,fragmentList.get(index));
        //一定要提交，坑爹啊
        transaction.commit();
        //关闭侧拉菜单
        activityMain.closeDrawers();
    }

    /**
     * 初始化所有片段
     */
    private void initFragments() {
        fragmentManager = getSupportFragmentManager();
        //初始化所有的fragment
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragmnet());
        fragmentList.add(new ImageFragmnet());
        fragmentList.add(new LikeFragmnet());
        fragmentList.add(new ChatFragmnet());
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        toolbarActivityMain.setTitle("新闻");
        toolbarActivityMain.setLogo(R.mipmap.ic_launcher);
        toolbarActivityMain.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbarActivityMain);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constant.GET_IMAGE_FROM_SERVICE && resultCode == RESULT_OK) {

            String imgStr = data.getStringExtra("data");

            //把string转化成bitmap
            byte[] decode = Base64.decode(imgStr, Base64.DEFAULT);

            Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);

            circleImageView.setImageBitmap(bitmap);

        } else if (requestCode == Constant.GET_IMAGE_FROM_CAMERA && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

            String imgStr = Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);

            App.ref.child(App.user.getUid()).setValue(imgStr);

            circleImageView.setImageBitmap(bitmap);

        } else if (requestCode == Constant.GET_IMAGE_FROM_GALLERY && resultCode == RESULT_OK) {

            Uri imgUri = data.getData();

            ContentResolver contentResolver = getContentResolver();

            try {
                //非常大的图片流
                InputStream in = contentResolver.openInputStream(imgUri);

                Rect rect = new Rect(0,0,96,96);
                BitmapFactory.Options opts = new BitmapFactory.Options();

                opts.inSampleSize = 5;
                //对流压缩处理
                Bitmap bitmap = BitmapFactory.decodeStream(in, rect, opts);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

                String imgStr = Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);

                App.ref.child(App.user.getUid()).setValue(imgStr);

                circleImageView.setImageBitmap(bitmap);

               /* Cursor cusor = contentResolver.query(imgUri, null, null, null, null);

                cusor.moveToFirst();

                String path = cusor.getString(1);
                String string = cusor.getString(0);
                String string1 = cusor.getString(2);
                String string2 = cusor.getString(3);


                BitmapFactory.Options opts = new BitmapFactory.Options();
                //采样值,缩放比例
                opts.inSampleSize = 50;//  真实的图片  imageview  ： 拿到真实图片的宽高和控件的宽高来比较，获取压缩比例

                Bitmap bitmap = BitmapFactory.decodeFile("data/data/com.example.news/temp.jpg", opts);

                circleImageView.setImageBitmap(bitmap);*/


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("请选择您的头像")
                .setItems(new String[]{"照相", "图库"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {

                            case 0:
                                fromCamera();
                                break;

                            case 1:
                                fromGallery();
                                break;
                        }

                    }
                }).show();

    }

    private void fromGallery() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");//image图片类型,*所以的后缀名：.jpg,.png,.gif.........
        startActivityForResult(intent, Constant.GET_IMAGE_FROM_GALLERY);

    }

    private void fromCamera() {
        //
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constant.GET_IMAGE_FROM_CAMERA);

    }

}
