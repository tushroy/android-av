/*******************************************************************************
 * Copyright (c) 2013 Jeff Mixon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * (or any later version, at your option)  which accompanies this distribution,
 * and is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Jeff - initial API and implementation
 ******************************************************************************/
package com.steelthorn.android.av;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Environment;

class Util
{
	public static List<PackageInfo> getInstalledPackages(Context ctx)
	{
		List<PackageInfo> packages = ctx.getPackageManager().getInstalledPackages(0);

		List<PackageInfo> nonSystemPackages = new ArrayList<PackageInfo>();

		for (PackageInfo pack : packages)
		{
			if ((pack.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM)
				continue;

			nonSystemPackages.add(pack);
		}

		return nonSystemPackages;
	}

	public static List<File> getAllFiles(Context ctx)
	{
		String base = Environment.getExternalStorageDirectory().toString();
		File baseFile = new File(base + "/external_sd");

		File[] files = baseFile.listFiles();

		List<File> result = new ArrayList<File>();

		for (int i = 0; i < files.length; i++)
		{
			if (!files[i].isDirectory())
				result.add(files[i]);
		}

		return result;
	}

	public static IScanDefinitionProvider getDevDefinitionProvider()
	{
		return new IScanDefinitionProvider()
		{

			@Override
			public List<IScanDefinitionGroup> getDefinitions()
			{
				// TODO Auto-generated method stub
				return Util.getDevDefinitions();
			}
		};
	}

	public static List<IScanDefinitionGroup> getDevDefinitions()
	{
		final List<IScanDefinition> def = new ArrayList<IScanDefinition>();

		def.add(new IScanDefinition()
		{
			public int getDefinitionId()
			{
				return 1;
			}

			public int getMatchSize()
			{
				return 23;
			}

			public byte[] getHashValue()
			{

				return Base64.decode("3YnpxrvKu5hZxi0m/FkpE+pUcwQ=", Base64.DEFAULT);

			}

			public double getWeight()
			{
				return 1;
			}

			public long getMatchPosition()
			{
				// TODO Auto-generated method stub
				return 0;
			}

		});

		IScanDefinitionGroup group = new IScanDefinitionGroup()
		{

			public List<IScanDefinition> getDefinitions()
			{
				return def;
			}

			public byte getDefinitionType()
			{
				// TODO Auto-generated method stub
				return DefinitionType.ANDROID_PACKAGE;
			}

			public int getDefinitionGroupId()
			{
				// TODO Auto-generated method stub
				return 1;
			}
		};

		List<IScanDefinitionGroup> allDefs = new ArrayList<IScanDefinitionGroup>();
		allDefs.add(group);

		return allDefs;
	}

	public static byte[] truncateArray(byte[] original, int newLength)
	{
		byte[] smaller = new byte[newLength];

		for (int i = 0; i < newLength; i++)
			smaller[i] = original[i];

		return smaller;
	}
}

class DefinitionType
{
	public static final byte ANDROID_PACKAGE = 1;
}
