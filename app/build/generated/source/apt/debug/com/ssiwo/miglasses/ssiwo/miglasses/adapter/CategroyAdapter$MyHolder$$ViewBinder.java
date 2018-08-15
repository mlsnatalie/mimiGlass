// Generated code from Butter Knife. Do not modify!
package com.ssiwo.miglasses.ssiwo.miglasses.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CategroyAdapter$MyHolder$$ViewBinder<T extends com.ssiwo.miglasses.ssiwo.miglasses.adapter.CategroyAdapter.MyHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493046, "field 'ivMain'");
    target.ivMain = finder.castView(view, 2131493046, "field 'ivMain'");
    view = finder.findRequiredView(source, 2131493047, "field 'tvDes'");
    target.tvDes = finder.castView(view, 2131493047, "field 'tvDes'");
  }

  @Override public void unbind(T target) {
    target.ivMain = null;
    target.tvDes = null;
  }
}
