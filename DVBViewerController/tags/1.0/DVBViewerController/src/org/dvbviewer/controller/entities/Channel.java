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
package org.dvbviewer.controller.entities;

import org.dvbviewer.controller.data.DbConsts.ChannelTbl;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * The Class Channel.
 * 
 * @author RayBa
 * @date 07.04.2012
 */
public class Channel implements Comparable<Channel>, Parcelable {

	public static final int	FLAG_FAV				= 1 << 0;	// 0x01

	public static final int	FLAG_ADDITIONAL_AUDIO	= 1 << 1;	// 0x02

	public static final int	FLAG_PENDING_UPDATE		= 1 << 2;	// 0x03

	private Long			id;

	private String			name;

	private Integer			position;

	private long			epgID;

	private long			favID;

	private int				favPosition				= -1;

	private int				flags					= 0;

	private String			logoUrl;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 * @author RayBa
	 * @date 07.04.2012
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 * @author RayBa
	 * @date 07.04.2012
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the epg id.
	 * 
	 * @return the epg id
	 * @author RayBa
	 * @date 07.04.2012
	 */
	public long getEpgID() {
		return epgID;
	}

	/**
	 * Sets the epg id.
	 * 
	 * @param epgID
	 *            the new epg id
	 * @author RayBa
	 * @date 07.04.2012
	 */
	public void setEpgID(long epgID) {
		this.epgID = epgID;
	}

	/**
	 * Gets the fav id.
	 *
	 * @return the fav id
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public long getFavID() { 
		return favID;
	}

	/**
	 * Sets the fav id.
	 *
	 * @param favID the new fav id
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public void setFavID(long favID) {
		this.favID = favID;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 * @author RayBa
	 * @date 07.04.2012
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 * @author RayBa
	 * @date 07.04.2012
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the position.
	 * 
	 * @return the position
	 * @author RayBa
	 * @date 07.04.2012
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 * 
	 * @param position
	 *            the new position
	 * @author RayBa
	 * @date 07.04.2012
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Channel another) {
		return this.position.compareTo(another.position);
	}

	/**
	 * To content values.
	 *
	 * @return the content values�
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public ContentValues toContentValues() {
		ContentValues result = new ContentValues();
		if (this.id != null && !this.id.equals(0l)) {
			result.put(ChannelTbl._ID, id);
		}
		if (this.epgID != 0l) {
			result.put(ChannelTbl.EPG_ID, this.epgID);
		}
		if (this.name != null && !TextUtils.isEmpty(this.name)) {
			result.put(ChannelTbl.NAME, this.name);
		}
		if (this.position != null) {
			result.put(ChannelTbl.POSITION, this.position);
		}
		if (this.favID != 0l) { 
			result.put(ChannelTbl.FAV_ID, this.favID);
		}
		if (this.favPosition >= 0) {
			result.put(ChannelTbl.FAV_POSITION, this.favPosition);
		}
		if (this.logoUrl != null && !TextUtils.isEmpty(this.logoUrl)) {
			result.put(ChannelTbl.LOGO_URL, this.logoUrl);
		}
		result.put(ChannelTbl.FLAGS, this.flags);
		return result;
	}

	/* (non-Javadoc)
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Read from parcel.
	 *
	 * @param src the src
	 * @author RayBa
	 * @date 07.04.2013
	 */
	private void readFromParcel(Parcel src) {
		long id = src.readLong();
		this.id = id == -1 ? null : id;
		this.name = src.readString();
		this.position = src.readInt();
		this.epgID = src.readLong();
		this.favPosition = src.readInt();
		this.flags = src.readInt();
		this.logoUrl = src.readString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.id == null ? -1 : this.id);
		dest.writeString(this.name);
		dest.writeInt(this.position);
		dest.writeLong(this.epgID);
		dest.writeInt(this.favPosition);
		dest.writeInt(this.flags);
		dest.writeString(this.logoUrl);
	}

	/**
	 * Instantiates a new channel.
	 *
	 * @param src the src
	 * @author RayBa
	 * @date 07.04.2013
	 */
	private Channel(Parcel src) {
		readFromParcel(src);
	}

	/**
	 * Instantiates a new channel.
	 *
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public Channel() {
	}

	/** The Constant CREATOR. */
	public static final Parcelable.Creator<Channel>	CREATOR	= new Parcelable.Creator<Channel>() {
																public Channel createFromParcel(Parcel src) {

																	return new Channel(src);
																}

																public Channel[] newArray(int size) {
																	return new Channel[size];
																}
															};

	/**
	 * The Class Fav.
	 *
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public static class Fav {

		/** The position. */
		public int		position;
		
		/** The id. */
		public long		id;
		
		/** The name. */
		public String	name;

	}

	/**
	 * Gets the flags.
	 *
	 * @return the flags
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public int getFlags() {
		return flags;
	}

	/**
	 * Sets the flags.
	 *
	 * @param flags the new flags
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public void setFlags(int flags) {
		this.flags = flags;
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag the new flag
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public void setFlag(int flag) {
		this.flags |= flag;
	}

	/**
	 * Unset flag.
	 *
	 * @param flag the flag
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public void unsetFlag(int flag) {
		this.flags &= ~flag;
	}

	/**
	 * Checks if is flag set.
	 *
	 * @param flag the flag
	 * @return true, if is flag set
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public boolean isFlagSet(int flag) {
		return (flag & flags) != 0;
	}

	/**
	 * Gets the fav position.
	 *
	 * @return the fav position
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public Integer getFavPosition() {
		return favPosition;
	}

	/**
	 * Sets the fav position.
	 *
	 * @param favPosition the new fav position
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public void setFavPosition(Integer favPosition) {
		this.favPosition = favPosition;
	}

	/**
	 * Gets the logo url.
	 *
	 * @return the logo url
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public String getLogoUrl() {
		return logoUrl;
	}

	/**
	 * Sets the logo url.
	 *
	 * @param logoUrl the new logo url
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}
