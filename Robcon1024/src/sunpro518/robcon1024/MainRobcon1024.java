package sunpro518.robcon1024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainRobcon1024 extends Activity {

	private String packageName = "sunpro518.robcon1024";
	//底部菜单栏组件
	private ImageView imgBotMenu1,imgBotMenu2,imgBotMenu3;
	private TextView textBotMenu1,textBotMenu2,textBotMenu3;
	private int imgWidth,offSet,winWidth;//图片宽度，偏移量，窗口宽度
	//ViewPager
	private View view1,view2,view3;
	private ViewPager mPager;
	private int currIndex = 0;
	//homeDescription();
	private TextView textHome1;
	//listViewExhibition();
	private ListView listEx;
	private int rowListView = 13;
	private String[] titleList = new String[rowListView];
	private int[] imgIdList = new int[rowListView];
	private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	//private ImageButton imgbutListLike;
	
	//菜单
	private Uri uri;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_robcon1024);
		//TODO onCreate()
		initGridLayout();
		
		initViewPager();
		
		homeDescription();
		
		listViewExhibition();
		
		setGetIntent();
		
	}
	
	/**
	 * 初始化gridLayout并且设定自适应分辨率
	 */
	public void initGridLayout() {
		
		imgBotMenu1 = (ImageView)findViewById(R.id.img_botmenu_1);
		imgBotMenu2 = (ImageView)findViewById(R.id.img_botmenu_2);
		imgBotMenu3 = (ImageView)findViewById(R.id.img_botmenu_3);
		textBotMenu1 = (TextView)findViewById(R.id.text_botmenu_1);
		textBotMenu2 = (TextView)findViewById(R.id.text_botmenu_2);
		textBotMenu3 = (TextView)findViewById(R.id.text_botmenu_3);
		//为底部菜单设置自适应于不同的屏幕分辨率
		imgWidth = BitmapFactory.decodeResource(getResources(), 
				R.drawable.icon1).getWidth();
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        winWidth = dm.widthPixels;// 获取分辨率宽度
        offSet = (winWidth / 3 - imgWidth) / 2;// 计算偏移量
        imgBotMenu1.setPadding(offSet, 0, offSet, 0);
        imgBotMenu2.setPadding(offSet, 0, offSet, 0);
        imgBotMenu3.setPadding(offSet, 0, offSet, 0);
        imgBotMenu1.setOnClickListener(new MyOnClickListener(0));
        imgBotMenu2.setOnClickListener(new MyOnClickListener(1));
        imgBotMenu3.setOnClickListener(new MyOnClickListener(2));
        textBotMenu1.setOnClickListener(new MyOnClickListener(0));
        textBotMenu2.setOnClickListener(new MyOnClickListener(1));
        textBotMenu3.setOnClickListener(new MyOnClickListener(2));
        
	}
	
	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		//获取view
		view1 = View.inflate(this, R.layout.main_1, null);
		view2 = View.inflate(this, R.layout.main_2, null);
		view3 = View.inflate(this, R.layout.main_3, null);
		//
		mPager = (ViewPager)findViewById(R.id.vPager);
		List<View> listviews = new ArrayList<View>();
		listviews.add(view1);
		listviews.add(view2);
		listviews.add(view3);
		
		mPager.setAdapter(new MyViewPagerAdapter(listviews));
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}
	
	/**
	 * ViewPager 适配器
	 */
	public class MyViewPagerAdapter extends PagerAdapter {

		public List<View> mListviews;
		public MyViewPagerAdapter(List<View> mListviews) {
			this.mListviews = mListviews;
		}
		
		@Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListviews.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListviews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListviews.get(arg1), 0);
            return mListviews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
		
	}

	/**
	 * ViewPager 监听器
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

        int one = offSet * 2 + imgWidth;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量

		public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                imgBotMenu1.setImageResource(R.drawable.icon1_2);
                imgBotMenu2.setImageResource(R.drawable.icon2);
                imgBotMenu3.setImageResource(R.drawable.icon3);
                textBotMenu1.setTextColor(getResources().getColor(R.color.blue));
                textBotMenu2.setTextColor(getResources().getColor(R.color.black));
                textBotMenu3.setTextColor(getResources().getColor(R.color.black));
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offSet, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                imgBotMenu1.setImageResource(R.drawable.icon1);
                imgBotMenu2.setImageResource(R.drawable.icon2_2);
                imgBotMenu3.setImageResource(R.drawable.icon3);
                textBotMenu2.setTextColor(getResources().getColor(R.color.blue));
                textBotMenu1.setTextColor(getResources().getColor(R.color.black));
                textBotMenu3.setTextColor(getResources().getColor(R.color.black));
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offSet, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                imgBotMenu1.setImageResource(R.drawable.icon1);
                imgBotMenu2.setImageResource(R.drawable.icon2);
                imgBotMenu3.setImageResource(R.drawable.icon3_2);
                textBotMenu3.setTextColor(getResources().getColor(R.color.blue));
                textBotMenu2.setTextColor(getResources().getColor(R.color.black));
                textBotMenu1.setTextColor(getResources().getColor(R.color.black));
                break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);// True:图片停在动画结束位置
            animation.setDuration(300);
            //cursor.startAnimation(animation);
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    }	
	
	/**
	 * BotMenu监听器
	 */
	public class MyOnClickListener implements View.OnClickListener {
	        private int index = 0;

	        public MyOnClickListener(int i) {
	            index = i;
	        }

			public void onClick(View v) {
	            mPager.setCurrentItem(index);  
	        }
	}

	 /**
	 * Home的动作
	  */
	private void homeDescription() {
		 textHome1 = (TextView)view1.findViewById(R.id.text_main1_1);
		 textHome1.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainRobcon1024.this, HomeDescription.class);
				startActivity(intent);
			}
		 });
	 }
 
	/**
	 * list动作
	 */
	private void listViewExhibition() {
		listEx = (ListView)view2.findViewById(R.id.listView_main2_catalog);
		//获取ListView中的title的String资源
		titleList = getResources().getStringArray(R.array.title_list_exhibition);
		for(int i = 0; i < titleList.length; i++) {
			imgIdList[i] = getResources().getIdentifier("rob_1_" + (i + 1)
					, "drawable", packageName);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("title",titleList[i]);
			map.put("imgId", imgIdList[i]);
			map.put("imgbut", R.drawable.icon3);
			list.add(map);
		}
		MySimpleAdapter adapterList = new MySimpleAdapter(MainRobcon1024.this, list,
				R.layout.item_listview_main2, 
				new String[] {"imgId","title","imgbut"}, 
				new int[] {R.id.img_item_listview,R.id.text_item_listview,
				R.id.imgBut_item_listview});
		listEx.setAdapter(adapterList);
		listEx.setOnItemClickListener( new MyListViewOnItemClickListener());
	}
	
	/**
	 * ListViewItemClickListener
	 */
	public class MyListViewOnItemClickListener implements OnItemClickListener{

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			//TODO
			Toast.makeText(MainRobcon1024.this,
							"列表" + (position + 1) , Toast.LENGTH_SHORT).show();
			Intent intentdetail = new Intent();
			//注意要放置String变量，否则为空
			intentdetail.putExtra("keyPosition", position + "");
			intentdetail.setClass(MainRobcon1024.this, DetailsShow.class);
			MainRobcon1024.this.startActivity(intentdetail);
			
			
			
					
		}
		
	}	
	
	/**
	 * ListView自定义的Adapter以及ImgBut的Listener
	 */
	public class MySimpleAdapter extends SimpleAdapter {

		//内部类，用于放置Button
		public class buttonViewHolder { 
	        ImageView appIcon; 
	        TextView appName; 
	        ImageButton buttonLike; 
	    }
		
		private Context mContext;
		private LayoutInflater mInflater; 
		private List<Map<String,Object>> mList = new ArrayList<Map<String,Object>>();
		private String[] from ;
		private int[] to;
		private buttonViewHolder holder;
		
		public MySimpleAdapter(Context context,
				List< Map<String, Object>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
			
			mContext = context;
			mList = data;
			MySimpleAdapter.this.from = new String[from.length];
			MySimpleAdapter.this.to = new int[to.length];
			mInflater = (LayoutInflater) mContext. getSystemService(
					Context . LAYOUT_INFLATER_SERVICE) ; 
			 System. arraycopy ( from , 0, this.from, 0, from.length ); 
		     System. arraycopy ( to, 0, this.to, 0, to.length ); 
		}

		@Override
		public int getCount() {
			
			return titleList.length;
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if ( convertView != null ) { 
	            holder = ( buttonViewHolder) convertView. getTag ( ) ; 
	        } else { 
	            convertView = mInflater. inflate ( R.layout.item_listview_main2, null ) ; 
	            holder = new buttonViewHolder( ) ; 
	            holder. appIcon = (ImageView)convertView.findViewById(R.id.img_item_listview); 
	            holder. appName = (TextView)convertView.findViewById(R.id.text_item_listview); 
	            holder. buttonLike = (ImageButton)convertView.
	            		findViewById(R.id.imgBut_item_listview); 
	            convertView. setTag(holder) ; 
	        } 
	        
	        HashMap < String , Object > appInfo = (HashMap<String, Object>) mList. get ( position ) ; 
	        if ( appInfo != null ) { 
	            String aname = (String)appInfo.get(from[1]); 
	            int mid = (Integer)appInfo.get(from[0]); 
	            int bid = (Integer)appInfo.get(from[2]); 
	            holder. appName.setText (aname); 
	            holder. appIcon.setImageDrawable(holder.appIcon.getResources().getDrawable(mid)) ;
	            if(holder.buttonLike.getDrawable() == null) {
	            	holder. buttonLike.setImageDrawable(holder.buttonLike.
	            		getResources() .getDrawable(bid)); }
	            holder. buttonLike.setOnClickListener( new lvButtonListener( position ) ) ; 
	        }  
	             
	        return convertView; 
		}

		@Override
		public void setViewImage(ImageView v, int value) {
			super.setViewImage(v, value);
		}

		@Override
		public void setViewImage(ImageView v, String value) {
			super.setViewImage(v, value);
		}

		@Override
		public void setViewText(TextView v, String text) {
			super.setViewText(v, text);
		}
		
		/**
		 * ListView的ImgBut的Listener
		 */
		class lvButtonListener implements OnClickListener { 
			private int position ; 
	    	//构造函数
	        lvButtonListener( int pos) { 
	            position = pos; 
	        } 
	        
	        public void onClick( View v) { 
	        	//TODO 未解决如何变换，只是临时的解决了！
	            @SuppressWarnings("unused")
				int vid= v.getId ( ) ; 
				Drawable butid = 
					getResources().getDrawable(R.drawable.icon3);
				Drawable butidthis= holder. buttonLike.getDrawable();
	            if ( butid == butidthis) {
	            	((ImageButton)v).setImageDrawable(
	            			v.getResources().getDrawable(R.drawable.icon3_2));
	            	Toast.makeText(MainRobcon1024.this, 
	            			"喜欢你" + (position + 1), Toast.LENGTH_SHORT).show();
	            }else{
	            	((ImageButton)v).setImageDrawable(
	            			v.getResources().getDrawable(R.drawable.icon3)); ; 
	            	Toast.makeText(MainRobcon1024.this, 
	            			"不喜欢" + (position + 1), Toast.LENGTH_SHORT).show();
	            }
	             
	        } 
	    } 
		
	} 
	
	//TODO
	/**
	 *尝试使用getIntent
	 */
	private void setGetIntent() {
		Intent intent = getIntent();
		String flagsIntent = intent.getStringExtra("flagItem");
		//注意为空的情况，所以要排除为空；
		if(flagsIntent != null && flagsIntent.equals("1")){
			mPager.setCurrentItem(1);
		}
		
	}
	
	/**
	 * Menu解析
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu_main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * Menu监听
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_search:
			Intent searchIntent = new Intent(MainRobcon1024.this,SearchActionBar.class);
			startActivity(searchIntent);
			return true;
		case R.id.menu_web:
			uri = Uri.parse(getResources().getString(
					getResources().getIdentifier("web_link", "string", packageName)));
			Intent intent = new Intent(Intent.ACTION_VIEW,uri);
			startActivity(intent);
			return true;
			default :
				return super.onOptionsItemSelected(item);
		
		}
		
	}
	 
}
