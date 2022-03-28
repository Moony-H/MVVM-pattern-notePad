package com.moony.mvvm_pattern_notepad.Tag

enum class FragmentTags(val fragment_name:String, val fragment_tag:String) {
    PAGE_NAV("navigation_page","navigation_page_tag"),
    PAGE_SCHEDULE("schedule_page","schedule_page_tag"),
    PAGE_SUBJECT_LIST("subject_list_page","subject_list_page_tag"),
    PAGE_RECORD("record_page","record_page_tag"),
    PAGE_SUBJECT_ADD("subject_add_page","subject_add_page_tag")

}