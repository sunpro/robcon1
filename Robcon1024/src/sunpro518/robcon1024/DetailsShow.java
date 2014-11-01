package sunpro518.robcon1024;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class DetailsShow  extends Activity{

	String position;//intent传递过来的position
	String[] titleList = new String[13];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details_show);
		
		//取得ListView的标题
		titleList = getResources().getStringArray(R.array.title_list_exhibition);
		
		Intent intent = getIntent();
		position = intent.getStringExtra("keyPosition"); 
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_details, menu);
		ActionBar actionbar = getActionBar();
		actionbar.setHomeButtonEnabled(true);
		actionbar.setDisplayHomeAsUpEnabled(true);
		actionbar.setTitle(titleList[Integer.parseInt(position)]);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home :
			Intent intent = new Intent(DetailsShow.this,MainRobcon1024.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("flagItem", "1");
			startActivity(intent);
			return true;
		case R.id.menu_like_details:
			Toast.makeText(DetailsShow.this, "likebutton", Toast.LENGTH_SHORT).show();
			
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
}
