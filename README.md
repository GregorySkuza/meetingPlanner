The application is designed based on the calendars of two people and
the expected length of the meeting will present a proposal for possible meeting dates.

Input:
- calendars for 2 people with specific working hours and already scheduled meetings
- expected duration of the meeting
As a result, the program should return the ranges in which meetings can be organized.

Meeting duration:[00:30] 

Example of Jsonfiles: 
Calendar1
{
  "working_hours": {
    "start": "09:00",
    "end": "19:55"
  },
  "planned_meeting": [
    {
      "start": "09:00",
      "end": "10:30"
    },
    {
      "start": "12:00",
      "end": "13:00"
    },
    {
      "start": "16:00",
      "end": "18:00"
    }
  ]
} 

Calendar2.json 
{
  "working_hours": {
    "start": "10:00",
    "end": "18:30"
},
  "planned_meeting": [
    {
      "start": "10:00",
      "end": "11:30"
    },
    {
      "start": "12:30",
      "end": "14:30"
    },
    {
      "start": "14:30",
      "end": "15:00"
    },
    {
      "start": "16:00",
      "end": "17:00"
    }
  ]
}
