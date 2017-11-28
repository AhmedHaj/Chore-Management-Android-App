package com.simulatedminds.choreapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by arielkim on 2017-11-22.
 */

public class ChoreListFragment extends Fragment {
    private RecyclerView mChoreRecyclerView;
    private ChoreAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //different cases for the menu item selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.new_chore:
                Chore chore = new Chore();
                ChoreLab.get(getActivity()).addChore(chore);
                Intent intent = ChorePagerActivity.newIntent(getActivity(), chore.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_chore_list, container, false);
        mChoreRecyclerView = (RecyclerView) view.findViewById(R.id.chore_recycler_view);
        //Job of positioning items and scrolling needs to be delegated to layoutmanager otherwise it crashes!
        //Keep in mind there is a gridlayoutmanager <-- check later reminder for myself
        mChoreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    //Saves changes made in the adapter and reflects them on the list
    public void onResume(){
        super.onResume();
        updateUI();
    }

    //"Inflates", creates the menu in the toolbar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_chore_list, menu);
    }


    //ViewHolder to inflate and own the layout, basically "actions" take place here
    private class ChoreHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Because views are being recycled through the RecyclerView, the
        // inflating (creation) of a view and the binding gets divided

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mCompletedImageView;
        private Chore mChore;


        public ChoreHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_chore,parent,false));
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView.findViewById(R.id.chore_title);
            mDateTextView = (TextView)itemView.findViewById(R.id.chore_date);
            mCompletedImageView = (ImageView) itemView.findViewById(R.id.chore_onTime);
        }

        public void bind(Chore chore){
            mChore = chore;
            mTitleTextView.setText(mChore.getTitle());
            mDateTextView.setText(mChore.getDate().toString());
            //COME BACK HERE TO IMPLEMENT THE CHANGE OF IMAGE FOR DIFFERENT STATUS OF COMPLETITION <--- REMINDER TO SELF
            mCompletedImageView.setVisibility(chore.isCompleted() ? View.VISIBLE : View.GONE);
        }
        @Override
        public void onClick(View view){
            Intent intent = ChorePagerActivity.newIntent(getActivity(),mChore.getId());
            startActivity(intent);
        }
    }

    //Adapter basically "holds" the actual data
    //Remember to check getItemViewType(int) when wanting to try to implement
    //Different views depending on the type of user that is clicking on the item!
    //REMINDER TO SELF! <---------------------------
    private class ChoreAdapter extends RecyclerView.Adapter<ChoreHolder>{
        private List<Chore> mChores;
        public ChoreAdapter(List<Chore> chores){
            mChores = chores;
        }

        @Override
        public ChoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ChoreHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ChoreHolder holder, int position) {
            Chore chore = mChores.get(position);
            holder.bind(chore);

        }

        @Override
        public int getItemCount() {
            return mChores.size();
        }

        public void setChores(List<Chore> chores){
            mChores = chores;
        }
    }

    private void updateUI(){
        ChoreLab choreLab = ChoreLab.get(getActivity());
        List<Chore> chores = choreLab.getChores();
        if(mAdapter == null){
            mAdapter = new ChoreAdapter(chores);
            mChoreRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setChores(chores);
            mAdapter.notifyDataSetChanged();
        }


    }
}
