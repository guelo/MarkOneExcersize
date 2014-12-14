package com.miguel.markoneexcerisize;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DemoObjectFragment extends Fragment {
	public static final String ARG_IMAGE = "object";

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		// The last two arguments ensure LayoutParams are inflated
		// properly.
		View rootView = inflater.inflate(
				R.layout.pic_fragment, container, false);

		((ImageView) rootView.findViewById(R.id.image)).setImageResource(getArguments().getInt(ARG_IMAGE));

		return rootView;
	}
}
