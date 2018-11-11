package hometest.android.tiki.ui.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import hometest.android.tiki.R;
import hometest.android.tiki.databinding.ItemKeywordBinding;
import hometest.android.tiki.domain.model.Keyword;

public class ListKeywordAdapter extends RecyclerView.Adapter<ListKeywordAdapter.KeywordBindingViewHolder> {
    public class KeywordBindingViewHolder extends RecyclerView.ViewHolder {
        ItemKeywordBinding binding;

        public KeywordBindingViewHolder(@NonNull ItemKeywordBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    private Context mContext;
    private Keyword mKeyword;

    public ListKeywordAdapter(Context context) {
        mContext = context;
    }

    public void addKeyword(Keyword keyword) {
        mKeyword = keyword;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KeywordBindingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ItemKeywordBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.item_keyword,
                viewGroup,
                false
        );
        return new KeywordBindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KeywordBindingViewHolder keywordBindingViewHolder, int index) {
        ItemKeywordViewModel viewModel = new ItemKeywordViewModel(mContext, mKeyword.getData().get(index));
        keywordBindingViewHolder.binding.setViewModel(viewModel);
        viewModel.bind();
    }

//    @Override
//    public void onBindViewHolder(KeywordBindingViewHolder holder, int position, List<Object> payloads) {
//        super.onBindViewHolder(holder, position, payloads);
//    }

    @Override
    public int getItemCount() {
        return mKeyword == null ? 0 : (mKeyword.getData() == null ? 0 : mKeyword.getData().size());
    }
}
