package com.akinsete.nytgithubsearch.ui.search;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akinsete.nytgithubsearch.R;
import com.akinsete.nytgithubsearch.data.network.model.responses.Owner;
import com.akinsete.nytgithubsearch.data.network.model.responses.Repo;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sundayakinsete on 2019-04-12.
 */
public class RepoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Repo> mRepos;
    private RepoListAdapterListener mRepoListAdapterListener;

    RepoListAdapter(List<Repo> repos, RepoListAdapterListener listener) {
        mRepos = repos;
        mRepoListAdapterListener = listener;
    }

    public interface RepoListAdapterListener {
        void onItemClick(String url);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_repo_list_item,
                parent,false);
        return new RepoListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RepoListAdapterViewHolder) holder).setData(mRepos.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    void updateData(List<Repo> repos) {
        mRepos = repos;
        notifyDataSetChanged();
    }

    void resetData() {
        mRepos.clear();
        notifyDataSetChanged();
    }


    public class RepoListAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_repo_owner_avatar) ImageView imgRepoAvatar;
        @BindView(R.id.tv_stars) TextView tvStarCount;
        @BindView(R.id.tv_repo_name) TextView tvRepoName;
        @BindView(R.id.tv_repo_description) TextView tvRepoDescription;


        RepoListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        public void setData(Repo repo) {

            tvRepoName.setText(repo.getName());

            tvStarCount.setText(repo.getStargazersCount().toString());

            tvRepoDescription.setText(repo.getDescription());

            Owner owner = repo.getOwner();
            if (owner != null && !TextUtils.isEmpty(owner.getAvatarUrl())) {
                Picasso.get()
                        .load(owner.getAvatarUrl())
                        .into(imgRepoAvatar);
            }


            itemView.setOnClickListener(v -> {
                if (mRepoListAdapterListener != null) {
                    mRepoListAdapterListener.onItemClick(repo.getHtmlUrl());
                }
            });
        }
    }
}
