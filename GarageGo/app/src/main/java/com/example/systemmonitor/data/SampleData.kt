package com.example.systemmonitor.data


object SampleData {

    val mechanicList = listOf(
        MechanicListResponse(1, "Gurugram Auto Care", 4.5, "Sector 29", true),
        MechanicListResponse(2, "DLF Motors", 4.2, "DLF Phase 3", true),
        MechanicListResponse(3, "Sohna Road Garage", 3.9, "Sohna Road", false),
        MechanicListResponse(4, "Cyber City Auto Works", 4.7, "Cyber City", true),
        MechanicListResponse(5, "Udyog Vihar Car Clinic", 4.0, "Udyog Vihar", true),
        MechanicListResponse(6, "Palam Vihar Auto Repair", 3.8, "Palam Vihar", false),
        MechanicListResponse(7, "MG Road Garage", 4.4, "MG Road", true),
        MechanicListResponse(8, "Sector 17 Car Care", 4.1, "Sector 17", true),
        MechanicListResponse(9, "Huda City Centre Autos", 3.7, "Huda City Centre", false),
        MechanicListResponse(10, "Golf Course Road Garage", 4.6, "Golf Course Road", true),
        MechanicListResponse(11, "Old Gurugram Motors", 4.3, "Old Gurugram", true),
        MechanicListResponse(12, "New Railway Road Auto Care", 3.6, "New Railway Road", false),
        MechanicListResponse(13, "Phase 2 Auto House", 4.1, "Udyog Vihar Phase 2", true),
        MechanicListResponse(14, "Ansal Plaza Garage", 4.0, "Ansal Plaza", true),
        MechanicListResponse(15, "IFFCO Chowk Car Clinic", 4.4, "IFFCO Chowk", true),
        MechanicListResponse(16, "South City Motorworks", 4.7, "South City", true),
        MechanicListResponse(17, "Bhiwadi Auto Garage", 3.5, "Bhiwadi", false),
        MechanicListResponse(18, "Sector 40 Auto Care", 4.2, "Sector 40", true),
        MechanicListResponse(19, "Sector 45 Motor Garage", 3.9, "Sector 45", true),
        MechanicListResponse(20, "Sector 56 Auto Point", 4.5, "Sector 56", true),
        MechanicListResponse(21, "Sector 23 Car Workshop", 3.8, "Sector 23", false),
        MechanicListResponse(22, "Sector 63 Auto Care", 4.1, "Sector 63", true),
        MechanicListResponse(23, "Sector 69 Motors", 4.3, "Sector 69", true),
        MechanicListResponse(24, "Sector 70 Auto Garage", 3.7, "Sector 70", false),
        MechanicListResponse(25, "Sector 82 Car Clinic", 4.0, "Sector 82", true)
    )

    val mechanicDetails = mapOf(
        // Key & Value 1 to MechanicDetailResponse
        1 to MechanicDetailResponse(
            id = 1,
            name = "Gurugram Auto Care",
            rating = 4.5,
            latitude = 28.4595,
            longitude = 77.0266,
            location_name = "Sector 29",
            address = "Shop 12, Main Market, Sector 29, Gurugram, Haryana 122001",
            available_services = listOf("Oil Change", "Brake Repair", "Engine Diagnostics"),
            working_hours = "Mon-Sat: 9:00 AM - 8:00 PM",
            phone_number = "+91-9876543210",
            is_open = true
        ),
        2 to MechanicDetailResponse(
            id = 2,
            name = "DLF Motors",
            rating = 4.2,
            latitude = 28.4744,
            longitude = 77.1044,
            location_name = "DLF Phase 3",
            address = "Plot 45, DLF Phase 3, Gurugram, Haryana 122002",
            available_services = listOf("Battery Replacement", "Tyre Change", "AC Repair"),
            working_hours = "Mon-Sun: 8:00 AM - 9:00 PM",
            phone_number = "+91-9812345678",
            is_open = true
        ),
        3 to MechanicDetailResponse(
            id = 3,
            name = "Sohna Road Garage",
            rating = 3.9,
            latitude = 28.4126,
            longitude = 77.0452,
            location_name = "Sohna Road",
            address = "Near Sohna Road Exit, Gurugram, Haryana 122018",
            available_services = listOf("Car Wash", "Oil Change", "Suspension Check"),
            working_hours = "Mon-Sun: 10:00 AM - 7:00 PM",
            phone_number = "+91-9123456780",
            is_open = false
        ),
        4 to MechanicDetailResponse(
            id = 4,
            name = "Cyber City Auto Works",
            rating = 4.7,
            latitude = 28.4948,
            longitude = 77.0886,
            location_name = "Cyber City",
            address = "Tower B, Cyber City, Gurugram, Haryana 122002",
            available_services = listOf("Engine Repair", "Brake Repair", "Transmission Service"),
            working_hours = "Mon-Sat: 9:00 AM - 10:00 PM",
            phone_number = "+91-9988776655",
            is_open = true
        ),
        5 to MechanicDetailResponse(
            id = 5,
            name = "Udyog Vihar Car Clinic",
            rating = 4.0,
            latitude = 28.5078,
            longitude = 77.0945,
            location_name = "Udyog Vihar",
            address = "Plot 7, Udyog Vihar Phase 4, Gurugram, Haryana 122015",
            available_services = listOf("Oil Change", "Battery Replacement", "Headlight Repair"),
            working_hours = "Mon-Sun: 8:00 AM - 6:00 PM",
            phone_number = "+91-9078563412",
            is_open = true
        ),
        6 to MechanicDetailResponse(
            id = 6,
            name = "Palam Vihar Auto Repair",
            rating = 3.8,
            latitude = 28.5093,
            longitude = 77.0301,
            location_name = "Palam Vihar",
            address = "B-101, Palam Vihar, Gurugram, Haryana 122017",
            available_services = listOf("Tyre Change", "Brake Repair", "Engine Diagnostics"),
            working_hours = "Mon-Sat: 9:00 AM - 7:00 PM",
            phone_number = "+91-9345678901",
            is_open = false
        ),
        7 to MechanicDetailResponse(
            id = 7,
            name = "MG Road Garage",
            rating = 4.4,
            latitude = 28.4795,
            longitude = 77.0720,
            location_name = "MG Road",
            address = "Shop 23, MG Road, Gurugram, Haryana 122001",
            available_services = listOf("AC Repair", "Car Wash", "Suspension Check"),
            working_hours = "Mon-Sun: 9:00 AM - 8:00 PM",
            phone_number = "+91-9567890123",
            is_open = true
        ),
        8 to MechanicDetailResponse(
            id = 8,
            name = "Sector 17 Car Care",
            rating = 4.1,
            latitude = 28.4630,
            longitude = 77.0345,
            location_name = "Sector 17",
            address = "Market Complex, Sector 17, Gurugram, Haryana 122001",
            available_services = listOf("Oil Change", "Battery Replacement", "Engine Repair"),
            working_hours = "Mon-Sat: 10:00 AM - 9:00 PM",
            phone_number = "+91-9234567890",
            is_open = true
        ),
        9 to MechanicDetailResponse(
            id = 9,
            name = "Huda City Centre Autos",
            rating = 3.7,
            latitude = 28.4595,
            longitude = 77.0713,
            location_name = "Huda City Centre",
            address = "Near Metro Station, Huda City Centre, Gurugram, Haryana 122001",
            available_services = listOf("Tyre Change", "Brake Repair", "AC Service"),
            working_hours = "Mon-Sun: 8:00 AM - 7:00 PM",
            phone_number = "+91-9787654321",
            is_open = false
        ),
        10 to MechanicDetailResponse(
            id = 10,
            name = "Golf Course Road Garage",
            rating = 4.6,
            latitude = 28.4512,
            longitude = 77.1020,
            location_name = "Golf Course Road",
            address = "Sector 56, Golf Course Road, Gurugram, Haryana 122011",
            available_services = listOf("Engine Diagnostics", "Transmission Service", "Car Wash"),
            working_hours = "Mon-Sat: 9:00 AM - 8:30 PM",
            phone_number = "+91-9876054321",
            is_open = true
        ),
        11 to MechanicDetailResponse(
            id = 11,
            name = "Old Gurugram Motors",
            rating = 4.3,
            latitude = 28.4688,
            longitude = 77.0261,
            location_name = "Old Gurugram",
            address = "Rajiv Chowk, Old Gurugram, Haryana 122001",
            available_services = listOf("Oil Change", "Brake Repair", "Headlight Repair"),
            working_hours = "Mon-Sun: 9:00 AM - 7:30 PM",
            phone_number = "+91-9156784320",
            is_open = true
        ),
        12 to MechanicDetailResponse(
            id = 12,
            name = "New Railway Road Auto Care",
            rating = 3.6,
            latitude = 28.4612,
            longitude = 77.0262,
            location_name = "New Railway Road",
            address = "Near Railway Station, Gurugram, Haryana 122001",
            available_services = listOf("Tyre Change", "Battery Replacement", "Car Wash"),
            working_hours = "Mon-Sat: 10:00 AM - 7:00 PM",
            phone_number = "+91-9345678123",
            is_open = false
        ),
        13 to MechanicDetailResponse(
            id = 13,
            name = "Phase 2 Auto House",
            rating = 4.1,
            latitude = 28.4924,
            longitude = 77.0912,
            location_name = "Udyog Vihar Phase 2",
            address = "Plot 34, Udyog Vihar Phase 2, Gurugram, Haryana 122016",
            available_services = listOf("Engine Repair", "AC Service", "Suspension Check"),
            working_hours = "Mon-Fri: 9:00 AM - 6:00 PM",
            phone_number = "+91-9865432109",
            is_open = true
        ),
        14 to MechanicDetailResponse(
            id = 14,
            name = "Ansal Plaza Garage",
            rating = 4.0,
            latitude = 28.4352,
            longitude = 77.0487,
            location_name = "Ansal Plaza",
            address = "Ansal Plaza Mall, Palam Vihar, Gurugram, Haryana 122017",
            available_services = listOf("Oil Change", "Brake Repair", "Tyre Change"),
            working_hours = "Mon-Sun: 10:00 AM - 8:00 PM",
            phone_number = "+91-9012345678",
            is_open = true
        ),
        15 to MechanicDetailResponse(
            id = 15,
            name = "IFFCO Chowk Car Clinic",
            rating = 4.4,
            latitude = 28.4632,
            longitude = 77.0612,
            location_name = "IFFCO Chowk",
            address = "IFFCO Chowk, Sector 29, Gurugram, Haryana 122001",
            available_services = listOf("AC Repair", "Engine Diagnostics", "Car Wash"),
            working_hours = "Mon-Sun: 8:00 AM - 9:00 PM",
            phone_number = "+91-9856734210",
            is_open = true
        ),
        16 to MechanicDetailResponse(
            id = 16,
            name = "South City Motorworks",
            rating = 4.7,
            latitude = 28.4258,
            longitude = 77.0983,
            location_name = "South City",
            address = "South City 2, Sector 50, Gurugram, Haryana 122018",
            available_services = listOf("Transmission Service", "Brake Repair", "Engine Repair"),
            working_hours = "Mon-Sat: 9:00 AM - 8:00 PM",
            phone_number = "+91-9876541230",
            is_open = true
        ),
        17 to MechanicDetailResponse(
            id = 17,
            name = "Bhiwadi Auto Garage",
            rating = 3.5,
            latitude = 28.3510,
            longitude = 76.9992,
            location_name = "Bhiwadi",
            address = "Bhiwadi Industrial Area, Gurugram, Haryana 122001",
            available_services = listOf("Oil Change", "Tyre Change", "Battery Replacement"),
            working_hours = "Mon-Sat: 9:00 AM - 6:00 PM",
            phone_number = "+91-9234786510",
            is_open = false
        ),
        18 to MechanicDetailResponse(
            id = 18,
            name = "Sector 40 Auto Care",
            rating = 4.2,
            latitude = 28.4481,
            longitude = 77.0631,
            location_name = "Sector 40",
            address = "Sector 40, Gurugram, Haryana 122001",
            available_services = listOf("AC Service", "Car Wash", "Engine Repair"),
            working_hours = "Mon-Sun: 9:00 AM - 8:00 PM",
            phone_number = "+91-9678452310",
            is_open = true
        ),
        19 to MechanicDetailResponse(
            id = 19,
            name = "Sector 45 Motor Garage",
            rating = 3.9,
            latitude = 28.4382,
            longitude = 77.0701,
            location_name = "Sector 45",
            address = "Sector 45, Gurugram, Haryana 122003",
            available_services = listOf("Brake Repair", "Suspension Check", "Headlight Repair"),
            working_hours = "Mon-Sat: 10:00 AM - 7:00 PM",
            phone_number = "+91-9123478560",
            is_open = true
        ),
        20 to MechanicDetailResponse(
            id = 20,
            name = "Sector 56 Auto Point",
            rating = 4.5,
            latitude = 28.4512,
            longitude = 77.1045,
            location_name = "Sector 56",
            address = "Sector 56, Gurugram, Haryana 122011",
            available_services = listOf("Engine Diagnostics", "Battery Replacement", "AC Repair"),
            working_hours = "Mon-Sun: 8:00 AM - 9:00 PM",
            phone_number = "+91-9786504321",
            is_open = true
        ),
        21 to MechanicDetailResponse(
            id = 21,
            name = "Sector 23 Car Workshop",
            rating = 3.8,
            latitude = 28.5012,
            longitude = 77.0302,
            location_name = "Sector 23",
            address = "Sector 23, Gurugram, Haryana 122017",
            available_services = listOf("Oil Change", "Tyre Change", "Car Wash"),
            working_hours = "Mon-Sat: 9:00 AM - 7:00 PM",
            phone_number = "+91-9898765430",
            is_open = false
        ),
        22 to MechanicDetailResponse(
            id = 22,
            name = "Sector 63 Auto Care",
            rating = 4.1,
            latitude = 28.5010,
            longitude = 77.0702,
            location_name = "Sector 63",
            address = "Sector 63, Gurugram, Haryana 122102",
            available_services = listOf("Brake Repair", "Engine Repair", "Transmission Service"),
            working_hours = "Mon-Sun: 10:00 AM - 8:00 PM",
            phone_number = "+91-9345672310",
            is_open = true
        ),
        23 to MechanicDetailResponse(
            id = 23,
            name = "Sector 69 Motors",
            rating = 4.3,
            latitude = 28.5087,
            longitude = 77.0620,
            location_name = "Sector 69",
            address = "Sector 69, Gurugram, Haryana 122101",
            available_services = listOf("AC Repair", "Battery Replacement", "Engine Diagnostics"),
            working_hours = "Mon-Sat: 9:00 AM - 8:00 PM",
            phone_number = "+91-9786512340",
            is_open = true
        ),
        24 to MechanicDetailResponse(
            id = 24,
            name = "Sector 70 Auto Garage",
            rating = 3.7,
            latitude = 28.5089,
            longitude = 77.0588,
            location_name = "Sector 70",
            address = "Sector 70, Gurugram, Haryana 122101",
            available_services = listOf("Car Wash", "Oil Change", "Suspension Check"),
            working_hours = "Mon-Sun: 9:00 AM - 7:00 PM",
            phone_number = "+91-9912345678",
            is_open = false
        ),
        25 to MechanicDetailResponse(
            id = 25,
            name = "Sector 82 Car Clinic",
            rating = 4.0,
            latitude = 28.5025,
            longitude = 76.9701,
            location_name = "Sector 82",
            address = "Sector 82, Gurugram, Haryana 122004",
            available_services = listOf("Tyre Change", "Engine Repair", "Brake Repair"),
            working_hours = "Mon-Sat: 9:00 AM - 6:30 PM",
            phone_number = "+91-9876501234",
            is_open = true
        )
    )

    val bookingsList = listOf(
        BookingItem(
            mechanicName = "Precision Auto Care",
            date = "March 12, 2026",
            plateNumber = "ABC-1234",
            vehicleModel = "Honda Civic"
        ),
        BookingItem(
            mechanicName = "Elite Mechanics",
            date = "May 24, 2026",
            plateNumber = "XYZ-9876",
            vehicleModel = "Toyota Camry"
        ),
        BookingItem(
            mechanicName = "City Garage",
            date = "Nov 05, 2023 - 09:00 AM",
            plateNumber = "ABC-1234",
            vehicleModel = "Honda Civic"
        )

    )

    val serviceCategories = listOf("All Services", "Oil Change", "Brake Repair", "Car Wash", "AC Repair")
}