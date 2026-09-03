package com.example.systemmonitor.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemmonitor.common.Resource
import com.example.systemmonitor.data.BookingItem
import com.example.systemmonitor.data.MechanicDetailResponse
import com.example.systemmonitor.data.MechanicListResponse
import com.example.systemmonitor.data.ServiceRequestCreate
import com.example.systemmonitor.data.ServiceRequestResponse
import com.example.systemmonitor.repository.MechanicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MechanicViewModel @Inject constructor(
    private val repository: MechanicRepository
) : ViewModel() {

    private val _mechanicListState = MutableStateFlow<Resource<List<MechanicListResponse>>>(Resource.Loading())
    val mechanicListState: StateFlow<Resource<List<MechanicListResponse>>> = _mechanicListState.asStateFlow()

    private val _mechanicDetailState = MutableStateFlow<Resource<MechanicDetailResponse>>(Resource.Loading())
    val mechanicDetailState: StateFlow<Resource<MechanicDetailResponse>> = _mechanicDetailState.asStateFlow()

    private val _requestState = MutableStateFlow<Resource<ServiceRequestResponse>>(Resource.Loading())
    val requestState: StateFlow<Resource<ServiceRequestResponse>> = _requestState.asStateFlow()

    private val _bookings = MutableStateFlow<List<BookingItem>>(emptyList())
    val bookings: StateFlow<List<BookingItem>> = _bookings.asStateFlow()

    fun loadMechanics() {
        viewModelScope.launch {
            _mechanicListState.value = Resource.Loading()
            val result = repository.getMechanicList()
            _mechanicListState.value = result
        }
    }

    fun loadMechanicDetail(mechanicId: Int) {
        viewModelScope.launch {
            _mechanicDetailState.value = Resource.Loading()
            val result = repository.getMechanicDetail(mechanicId)
            _mechanicDetailState.value = result
        }
    }

    fun submitServiceRequest(request: ServiceRequestCreate) {
        viewModelScope.launch {
            _requestState.value = Resource.Loading()
            val result = repository.serviceRequest(request)
            _requestState.value = result
        }
    }

    fun resetRequestState() {
        _requestState.value = Resource.Loading()
    }

    fun loadBookings() {
        _bookings.value = repository.getBookingHistory()
    }

}