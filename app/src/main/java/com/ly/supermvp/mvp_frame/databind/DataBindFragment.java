/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ly.supermvp.mvp_frame.databind;

import android.os.Bundle;
import android.view.View;

import com.ly.supermvp.mvp_frame.model.IModel;
import com.ly.supermvp.mvp_frame.presenter.FragmentPresenter;
import com.ly.supermvp.mvp_frame.view.IDelegate;


/**
 * 集成数据-视图绑定的Fragment基类(Presenter层)
 *@author kymjs (http://www.kymjs.com/) on 10/23/15.
 * @param <T> View层代理类
 */
public abstract class DataBindFragment<T extends IDelegate> extends
        FragmentPresenter<T> {

    protected DataBinder binder;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder = getDataBinder();
    }

    public abstract DataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data) {
        if (binder != null)
            binder.viewBindModel(viewDelegate, data);
    }
}
