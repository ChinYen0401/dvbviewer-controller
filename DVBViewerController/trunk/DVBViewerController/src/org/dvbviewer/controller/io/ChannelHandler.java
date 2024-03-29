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
package org.dvbviewer.controller.io;

import java.util.ArrayList;
import java.util.List;

import org.dvbviewer.controller.entities.Channel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

/**
 * The Class ChannelHandler.
 *
 * @author RayBa
 * @date 07.04.2013
 */
public class ChannelHandler extends DefaultHandler {

	List<Channel>	channelList		= null;
	Channel		currentChannel	= null;

	/**
	 * Parses the.
	 *
	 * @param xml the xml
	 * @return the list�
	 * @author RayBa
	 * @date 07.04.2013
	 */
	public List<Channel> parse(String xml) {
		RootElement channels = new RootElement("channels");
		Element rootElement = channels.getChild("root");
		Element groupElement = rootElement.getChild("group");
		Element channelElement = groupElement.getChild("channel");
		Element logoElement = channelElement.getChild("logo");

		channels.setStartElementListener(new StartElementListener() {

			@Override
			public void start(Attributes attributes) {
				channelList = new ArrayList<Channel>();
			}
		});

		channelElement.setStartElementListener(new StartElementListener() {
			public void start(Attributes attributes) {
				currentChannel = new Channel();
				currentChannel.setId(Long.valueOf(attributes.getValue("ID")));
				currentChannel.setPosition(Integer.valueOf(attributes.getValue("nr")));			
				currentChannel.setName(attributes.getValue("name"));			
				currentChannel.setEpgID(Long.valueOf(attributes.getValue("EPGID")));
			}
		});

		channelElement.setEndElementListener(new EndElementListener() {

			@Override
			public void end() {
				channelList.add(currentChannel);
			}
		});


		logoElement.setEndTextElementListener(new EndTextElementListener() {

			@Override
			public void end(String body) {
				currentChannel.setLogoUrl(body);
			}
			
		});
		

		try {
			Xml.parse(xml, channels.getContentHandler());
			return channelList;
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
