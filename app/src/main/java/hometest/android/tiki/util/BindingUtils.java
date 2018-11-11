package hometest.android.tiki.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.ui.home.ListKeywordAdapter;

public class BindingUtils {
    public BindingUtils() {

    }

    private static final String TAG = BindingUtils.class.getSimpleName();

    @BindingAdapter("bind:keyword")
    public static void loadKeyword(RecyclerView recyclerView, Keyword keyword) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(
                    recyclerView.getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false));
        }

        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            adapter = new ListKeywordAdapter(recyclerView.getContext());
            recyclerView.setAdapter(adapter);
        }
        ListKeywordAdapter keywordAdapter = (ListKeywordAdapter) adapter;
        keywordAdapter.addKeyword(keyword);
    }

    @BindingAdapter("bind:keywordBackground")
    public static void setBackground(LinearLayout layout, int color) {
        Drawable background = layout.getBackground();
        if (background == null) {
            background = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFF616261, 0xFF131313});
            ((GradientDrawable) background).setColor(color);
            ((GradientDrawable) background).setCornerRadius(24);
        }

        layout.setBackgroundDrawable(background);
    }

    @BindingAdapter("bind:visibility")
    public static void setVisibility(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

}
