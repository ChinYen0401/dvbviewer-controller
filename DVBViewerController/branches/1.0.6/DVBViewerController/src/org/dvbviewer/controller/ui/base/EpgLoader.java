/*
 * Copyright � 2013 dvbviewer-controller Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.dvbviewer.controller.ui.base;

import org.dvbviewer.controller.ui.fragments.ChannelEpg.EpgDateInfo;

import android.content.Context;

/**
 * The Class EpgLoader.
 *
 * @param <D> the
 * @author RayBa
 * @date 07.04.2013
 */
public abstract class EpgLoader<D> extends AsyncLoader<D>{
	
	EpgDateInfo mDateInfo;
	long mEpgDate;

	/**
	 * Instantiates a new epg loader.
	 *
	 * @param context the context
	 * @param dateInfo the date info
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public EpgLoader(Context context, EpgDateInfo dateInfo) {
		super(context);
		mDateInfo = dateInfo;
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.content.AsyncTaskLoader#onForceLoad()
	 */
	@Override
	protected void onForceLoad() {
		super.onForceLoad();
		mEpgDate = mDateInfo.getEpgDate().getTime();
	}
	
	/* (non-Javadoc)
	 * @see android.support.v4.content.Loader#takeContentChanged()
	 */
	@Override
	public boolean takeContentChanged() {
		return mEpgDate != mDateInfo.getEpgDate().getTime();
	}

}
