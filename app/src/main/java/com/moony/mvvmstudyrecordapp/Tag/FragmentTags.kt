package com.moony.mvvmstudyrecordapp.Tag

enum class FragmentTags(val fragment_name:String, val fragment_tag:String) {
    PAGE_NAV("navigation_page","navigation_page_tag"),
    PAGE_SCHEDULE("schedule_page","schedule_page_tag"),
    PAGE_SUBJECT_LIST("subject_list_page","subject_list_page_tag"),
    PAGE_SUBJECT_ADD("subject_add_page","subject_add_page_tag"),
    PAGE_SUBJECT_DETAIL("subject_detail_page","subject_detail_page_tag"),
    PAGE_RECORD_LIST("record_list_page","record_list_page_tag"),
    PAGE_RECORD_ADD("record_add_page","record_add_page_tag")

}