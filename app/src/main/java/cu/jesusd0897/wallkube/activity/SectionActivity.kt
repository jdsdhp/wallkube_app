/*
 * Copyright (c) 2020 jesusd0897.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cu.jesusd0897.wallkube.activity

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import cu.jesusd0897.wallkube.R
import cu.jesusd0897.wallkube.database.model.Section
import cu.jesusd0897.wallkube.fragment.PicturesFragment
import cu.jesusd0897.wallkube.util.KNav
import cu.jesusd0897.wallkube.util.KUtil

class SectionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_gallery)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val section = intent.getParcelableExtra(KNav.EXTRA_ITEM_TAG) as Section
        KNav.replaceFragment(
            supportFragmentManager, PicturesFragment.newInstance(section.id), R.id.container
        )
        toolbar.title = getString(KUtil.getSectionTitle(section.id))
    }

}
