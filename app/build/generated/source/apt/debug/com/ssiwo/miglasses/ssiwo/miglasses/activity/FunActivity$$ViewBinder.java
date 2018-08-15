// Generated code from Butter Knife. Do not modify!
package com.ssiwo.miglasses.ssiwo.miglasses.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FunActivity$$ViewBinder<T extends com.ssiwo.miglasses.ssiwo.miglasses.activity.FunActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493015, "field 'ivTop'");
    target.ivTop = finder.castView(view, 2131493015, "field 'ivTop'");
    view = finder.findRequiredView(source, 2131493017, "field 'ivCenter'");
    target.ivCenter = finder.castView(view, 2131493017, "field 'ivCenter'");
    view = finder.findRequiredView(source, 2131493020, "field 'lv'");
    target.lv = finder.castView(view, 2131493020, "field 'lv'");
    view = finder.findRequiredView(source, 2131493021, "field 'UnityView'");
    target.UnityView = finder.castView(view, 2131493021, "field 'UnityView'");
    view = finder.findRequiredView(source, 2131493022, "field 'ivBottom'");
    target.ivBottom = finder.castView(view, 2131493022, "field 'ivBottom'");
    view = finder.findRequiredView(source, 2131493024, "field 'back' and method 'onClick'");
    target.back = finder.castView(view, 2131493024, "field 'back'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493025, "field 'llMain'");
    target.llMain = finder.castView(view, 2131493025, "field 'llMain'");
    view = finder.findRequiredView(source, 2131493026, "field 'gvMain'");
    target.gvMain = finder.castView(view, 2131493026, "field 'gvMain'");
    view = finder.findRequiredView(source, 2131493019, "field 'srl'");
    target.srl = finder.castView(view, 2131493019, "field 'srl'");
    view = finder.findRequiredView(source, 2131493023, "field 'fb' and method 'onClick'");
    target.fb = finder.castView(view, 2131493023, "field 'fb'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131493018, "field 'shopTitle'");
    target.shopTitle = finder.castView(view, 2131493018, "field 'shopTitle'");
    view = finder.findRequiredView(source, 2131493016, "field 'ivErweima'");
    target.ivErweima = finder.castView(view, 2131493016, "field 'ivErweima'");
  }

  @Override public void unbind(T target) {
    target.ivTop = null;
    target.ivCenter = null;
    target.lv = null;
    target.UnityView = null;
    target.ivBottom = null;
    target.back = null;
    target.llMain = null;
    target.gvMain = null;
    target.srl = null;
    target.fb = null;
    target.shopTitle = null;
    target.ivErweima = null;
  }
}
