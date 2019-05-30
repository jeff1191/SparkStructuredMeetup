package com.github.jeff1191.datatype


object DataModel {
  case class MeetupRSVGevent(member: Option[Member], response: String, visibility: String, event: Option[Event],
                             mtime: Long, guests: Int, rsvp_id: Long, group: Group, venue: Option[Venue])
  case class Member( member_name: String, photo: Option[String], member_id: String)
  case class Venue( lon: Double, venue_name: String, venue_id: Int, lat: Double)
  case class Group( group_name: String, group_city: String, group_lat: Double, group_urlname: String, group_id: String,
                    group_country: String, group_lon: Double, group_topics: List[Group_topics] )
  case class Event( time:  Option[Long], event_url: String, event_id: String, event_name: String)
  case class Group_topics( urlkey: String, topic_name: String)
}
