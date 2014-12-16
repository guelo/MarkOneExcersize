package com.miguel.markoneexcerisize;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

	private ViewPager mViewPager;
	private DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
	private static final float MIN_SCALE = 0.85f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDemoCollectionPagerAdapter =
				new DemoCollectionPagerAdapter(
						getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mDemoCollectionPagerAdapter);
		int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20 * 2, getResources().getDisplayMetrics());

		mViewPager.setPadding(200, 0, 200, 0);
		mViewPager.setClipToPadding(false);
		mViewPager.setPageMargin(-200);
		mViewPager.setOffscreenPageLimit(10);

		mViewPager.setPageTransformer(true , new ViewPager.PageTransformer() {
			public static final String TAG = "TAG";

			@Override
			public void transformPage(View view, float position) {
				int pageWidth = view.getWidth();
				int pageHeight = view.getHeight();

				float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
				float vertMargin = pageHeight * (1 - scaleFactor) / 2;
				float horzMargin = pageWidth * (1 - scaleFactor) / 2;
				if (position < 0) {
					view.setTranslationX(horzMargin - vertMargin / 2);
				} else {
					view.setTranslationX(-horzMargin + vertMargin / 2);
				}

				double fromMiddle = position - 0.54347825;

				float scale = (float) (1 - Math.abs(fromMiddle / Math.sqrt(Math.pow(fromMiddle, 2) + 10)));

				double rotate = -45 * fromMiddle / Math.sqrt(Math.pow(fromMiddle, 2) + 1);

				view.setRotationY((float) rotate);
				view.setScaleX(scale);
				view.setScaleY(scale);
				view.setAlpha(scale);

			}});

		mViewPager.setCurrentItem(1);

	}

	public class DemoCollectionPagerAdapter extends FragmentPagerAdapter {
		public DemoCollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		int[] imageIds = {R.drawable.circle_fill, R.drawable.rect_fill, R.drawable.triangle_fill};

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = new DemoObjectFragment();
			Bundle args = new Bundle();
			// Our object is just an integer :-P
			args.putInt(DemoObjectFragment.ARG_IMAGE, imageIds[i%3]);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return 31;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "OBJECT " + (position + 1);
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
