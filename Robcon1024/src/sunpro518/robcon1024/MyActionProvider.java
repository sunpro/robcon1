package sunpro518.robcon1024;

import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class MyActionProvider extends ActionProvider{

	private Context context;
	private LayoutInflater inflater;
	private View view;
	private ImageView button;
	public MyActionProvider(Context context) {
		super(context);
		this.context = context;
		inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.search_actionbar, null);
		
	}

	@Override
	public View onCreateActionView() {

		return null;
	}

}
